package com.otl.otl.controller;


import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.StudyDTO;
import com.otl.otl.dto.StudyListDTO;
import com.otl.otl.dto.TodolistDTO;
import com.otl.otl.service.MemberStudyService;
import com.otl.otl.service.StudyService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated // DTO 유효성 검사를 위한 어노테이션
@Log4j2
//@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;
    private final MemberStudyService memberStudyService;


    @Autowired
    public StudyController(StudyService studyService, MemberStudyService memberStudyService) {
        this.studyService = studyService;
        this.memberStudyService = memberStudyService;
    }



    /*
     < ref.>
    @GetMapping
    public List<MemberStudy> getAllTodolists(@AuthenticationPrincipal OAuth2User oauthUser) {
            List<memberstudyDTO> memberStudy = MemberStudyService.getall();
        return memberstudy;
    }
*/


//  <<< 모집 페이지 >>>
    /*
        - 모집 페이지 -> 모집 종료일 이전 스터디 조회
     */
    @GetMapping("/studyRoom_yu")
    public ResponseEntity<List<StudyListDTO>> findOpenStudies(@AuthenticationPrincipal OAuth2User oauthUser) {
        List<StudyListDTO> studies = studyService.findOpenStudies();

        // 추가 ㅠ

        log.info("StudyService result : " + studies);
        return ResponseEntity.ok(studies);
    }



}
