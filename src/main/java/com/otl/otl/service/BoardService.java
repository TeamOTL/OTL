package com.otl.otl.service;


import com.otl.otl.domain.Board;
import com.otl.otl.dto.BoardDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno); // 조회
    Board modify(BoardDTO boardDTO); // 수정
    void remove(Long bno); // 삭제
    //PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO); // 목록, 검색
}