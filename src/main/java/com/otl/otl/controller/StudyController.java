package com.otl.otl.controller;

import com.otl.otl.domain.Member;
import com.otl.otl.domain.MemberStudy;
import com.otl.otl.dto.MemberDTO;
import com.otl.otl.dto.StudyDTO;
import com.otl.otl.dto.TaskDTO;
import com.otl.otl.repository.MemberRepository;
import com.otl.otl.repository.MemberStudyRepository;
import com.otl.otl.service.StudyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Validated // DTO 유효성 검사를 위한 어노테이션
@Log4j2
public class StudyController {
    private final StudyService studyService;
    private final MemberRepository memberRepository;
    private final MemberStudyRepository memberStudyRepository;

    @Autowired
    public StudyController(StudyService studyService, MemberRepository memberRepository, MemberStudyRepository memberStudyRepository) {
        this.studyService = studyService;
        this.memberRepository = memberRepository;
        this.memberStudyRepository = memberStudyRepository;
    }

    // 사용자의 스터디룸 목록을 가져오는 API
    @GetMapping("/studyRooms")
    public ResponseEntity<List<StudyDTO>> getUserStudies(@AuthenticationPrincipal OAuth2User oauthUser) {
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        String email = (String) kakaoAccount.get("email");
        List<StudyDTO> studies = studyService.findUserStudies(email);
        return ResponseEntity.ok(studies);
    }



    // 스터디 업데이트 API
    @PutMapping("/studyRooms/{sno}/update")
    public ResponseEntity<Void> updateStudy(@PathVariable Long sno, @RequestBody StudyDTO studyDTO) {
        log.info("스터디 수정 요청 - 스터디 ID: {}", sno);
        try {
            studyService.updateStudy(sno, studyDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("스터디 수정 중 오류 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 스터디 삭제 API
    @DeleteMapping("/studyRooms/{sno}/delete")
    public ResponseEntity<Void> deleteStudy(@PathVariable Long sno) {
        log.info("스터디 삭제 요청 - 스터디 ID: {}", sno);
        try {
            studyService.deleteStudy(sno);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("스터디 삭제 중 오류 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // 멤버 제거 API
    @DeleteMapping("/api/studyMembers/{email}/remove")
    public ResponseEntity<Void> removeMember(@PathVariable String email) {
        log.info("스터디 멤버 제거 요청 - 이메일: {}", email);
        try {
            studyService.removeMemberByEmail(email);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("멤버 제거 중 오류 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/api/studyApplicants/{email}/accept")
    public ResponseEntity<Void> acceptApplicant(@PathVariable String email) {
        log.info("참가 신청자 승인 요청 - 이메일: {}", email);
        try {
            studyService.acceptApplicantByEmail(email);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("신청자 승인 중 오류 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/api/studyApplicants/{email}/reject")
    public ResponseEntity<Void> rejectApplicant(@PathVariable String email) {
        log.info("참가 신청자 거절 요청 - 이메일: {}", email);
        try {
            studyService.rejectApplicantByEmail(email);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("신청자 거절 중 오류 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
