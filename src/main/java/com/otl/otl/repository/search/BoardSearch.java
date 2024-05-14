package com.otl.otl.repository.search;

import com.otl.otl.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardSearch {

    // 삭제되지 않은 게시물을 페이지로 가져옵니다.
    Page<Board> findByIsDeletedFalse(Pageable pageable);

}