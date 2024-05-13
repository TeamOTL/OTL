package com.otl.otl.controller;


import com.otl.otl.dto.StudyDTO;
import com.otl.otl.service.StudyService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
