package com.otl.otl.controller;


import com.otl.otl.domain.Study;
import com.otl.otl.dto.StudyDTO;
import com.otl.otl.service.StudyService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
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

    @Autowired
    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/studyRoom_yu")
    public ResponseEntity<List<Study>> getUserStudies(@AuthenticationPrincipal OAuth2User oauthUser) {
        String email = oauthUser.getAttribute("email");
        List<Study> studies = studyService.findUserStudies(email);
        return ResponseEntity.ok(studies);
    }


@PostMapping("/studyCreate")
public ResponseEntity<String> studyCreate(@RequestBody StudyDTO studyDTO) {

    System.out.println("StudyApiController");
    System.out.println(studyDTO);

    log.info("게시글 저장 시도 : {}", studyDTO);



    //ajax resp으로 전달
    //status 200: 통신을 정상적으로 성공함
    return ResponseEntity.status(HttpStatus.CREATED).body("스터디 성공적으로 등록되었습니다.");

}

}
