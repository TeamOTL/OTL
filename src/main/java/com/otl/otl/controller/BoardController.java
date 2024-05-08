package com.otl.otl.controller;


import com.otl.otl.domain.Board;
import com.otl.otl.dto.BoardDTO;
import com.otl.otl.service.BoardService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated // DTO 유효성 검사를 위한 어노테이션
@Log4j2
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }


    // POST 요청을 처리하는 메소드, 게시글 저장
    @PostMapping("/saveBoard")
    public ResponseEntity<String> saveBoard(@Valid @RequestBody BoardDTO boardDTO) {
        // BoardService를 사용하여 게시글을 저장하고, 성공 여부를 확인합니다.
        // 게시글 저장 시도 로깅
        log.info("게시글 저장 시도 : {}", boardDTO);

        Long boardId = boardService.register(boardDTO);

        // 저장 결과에 따라 적절한 응답을 반환합니다.
        if (boardId != null) {
            log.info("게시글이 성공적으로 저장되었습니다. 게시글 ID: {}", boardId);
            return ResponseEntity.status(HttpStatus.CREATED).body("게시글이 성공적으로 등록되었습니다.");
        } else {
            log.info("게시글 저장 실패: {}", boardDTO);
            return ResponseEntity.badRequest().body("게시글 등록에 실패했습니다.");
        }
    }
}
