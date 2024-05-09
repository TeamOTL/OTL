package com.otl.otl.service;


import com.otl.otl.domain.Board;
import com.otl.otl.dto.BoardDTO;
import org.springframework.data.domain.Page;

public interface BoardService {
    //새로운 게시글을 등록합니다.
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno); // 조회
    Board modify(BoardDTO boardDTO); // 수정
    void remove(Long bno); // 삭제

    Page<BoardDTO> findBoards(int page, int size);
    //PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO); // 목록, 검색
}