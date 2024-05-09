package com.otl.otl.service;


import com.otl.otl.dto.BoardDTO;
import org.springframework.data.domain.Page;

public interface BoardService {
    //새로운 게시글을 등록합니다.
    Long register(BoardDTO boardDTO);

    // 페이징 처리를 통해 게시글 목록을 조회합니다.
    Page<BoardDTO> findBoards(int page, int size);
}