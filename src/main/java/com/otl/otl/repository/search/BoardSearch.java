package com.otl.otl.repository.search;

import com.otl.otl.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardSearch {
    List<Board> findByBoardTitleContaining(String keyword); // 제목에 특정 키워드가 포함된 게시물 검색

    // 삭제되지 않은 게시물을 페이지로 가져옵니다.
    Page<Board> findByIsDeletedFalse(Pageable pageable);

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}