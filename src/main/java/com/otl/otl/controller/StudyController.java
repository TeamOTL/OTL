package com.otl.otl.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.StudyListDTO;
import com.otl.otl.dto.customDTO.StudyCreateCustomDTO;
import com.otl.otl.service.MemberStudyService;
import com.otl.otl.service.StudyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated // DTO 유효성 검사를 위한 어노테이션
@Log4j2
//@RequiredArgsConstructor
public class StudyController {
    private final StudyService studyService;
    private MemberStudyService memberStudyService;

    @Autowired
    public StudyController(StudyService studyService, ObjectMapper objectMapper) {
        this.studyService = studyService;
        this.objectMapper = objectMapper;
    }

//    @GetMapping("/studyRoom_yu")
//    public ResponseEntity<List<Study>> getUserStudies(@AuthenticationPrincipal OAuth2User oauthUser) {
//        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
//        String email = (String) kakaoAccount.get("email");
//        List<Study> studies = studyService.findUserStudies(email);
//        return ResponseEntity.ok(studies);
//    }


    private final ObjectMapper objectMapper; // ObjectMapper를 통해 Map을 DTO로 변환

//    @PostMapping(value = "/studyCreate", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> studyCreate(@Valid @RequestBody Map<String, Object> studyDTOMap, @AuthenticationPrincipal OAuth2User oauthUser) {
//        log.info("PreCreating study with DTO: {}", studyDTOMap);
//
////        클라이언트 이메일 받아오기
//        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
//        String email = (String) kakaoAccount.get("email");
//        log.info("Email: {}", email);
//
//
//        try {
//            // Map 데이터를 StudyDTO로 변환
//            StudyCreateDTO studyCreateDTO = objectMapper.convertValue(studyDTOMap, StudyCreateDTO.class);
//            log.info("Converted StudyDTO: {}", studyCreateDTO);  // 여기서 DTO의 값을 확인
//
//
//            // 스터디 방 생성 서비스 호출
//            Long sno = studyService.register(studyCreateDTO, email);
//            log.info("Study created with ID: {}", sno);
//
//            return ResponseEntity.status(HttpStatus.CREATED).body("스터디 성공적으로 등록되었습니다.");
//        } catch (Exception e) {
//            log.error("Error creating study", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("스터디 생성에 실패하였습니다.");
//        }
//    }

    // StudyCreateCustomDTO를 사용하여 Study를 생성하는 메서드
    @PostMapping("/studyCreate")
    public ResponseEntity<String> studyCreate(@RequestBody StudyCreateCustomDTO studyCreateCustomDTO, @AuthenticationPrincipal OAuth2User oauthUser) {
        log.info("preCreated" + studyCreateCustomDTO);
        log.info("studyName:" + studyCreateCustomDTO.getStudyName());
        log.info("studyDescription:" + studyCreateCustomDTO.getStudyDescription());
        log.info("firstDate:" + studyCreateCustomDTO.getFirstDate());
        log.info("rStartDate:" + studyCreateCustomDTO.getRStartDate());
        log.info("rEndDate:" + studyCreateCustomDTO.getREndDate());

        log.info("dDay:" + studyCreateCustomDTO.getDDay());
        log.info("interests:" + studyCreateCustomDTO.getInterestsDTO());
        log.info("tasks:" + studyCreateCustomDTO.getTaskDTO());
        log.info("cno:" + studyCreateCustomDTO.getCno());


        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        String email = (String) kakaoAccount.get("email");
        log.info("Email: {}", email);


//        // Study 생성 서비스 호출
//        studyService.createStudy(studyCreateCustomDTO, email);
//
//
//        return ResponseEntity.status(HttpStatus.CREATED).body("스터디 성공적으로 등록되었습니다.");

        studyService.createStudy(studyCreateCustomDTO, email);

        return ResponseEntity.status(HttpStatus.CREATED).body("Study created successfully");
    }

//    @GetMapping("/studyList")
//    public List<StudyListDTO> getOpenStudies() {
//        List<StudyListDTO> studies = studyService.findOpenStudies();
//        log.info("StudyListDTO: " + studies);
//
//        return studies;
//    }


    @GetMapping("/studyList/{sno}")
    public ResponseEntity<List<StudyListDTO>> getMyStudyAcceptedAndSno(@AuthenticationPrincipal OAuth2User oauthUser, @PathVariable Long sno) {
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        String email = (String) kakaoAccount.get("email");
        List<StudyListDTO> studies = memberStudyService.getMyStudyAcceptedAndSno(email, sno);
        log.info("studies:"+studies);


        return ResponseEntity.ok(studies);
    }

}

