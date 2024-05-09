package com.otl.otl.repository;

import com.otl.otl.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.otl.otl.domain.Board;



import java.util.List;
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch{
    List<Board> findByBoardTitleContaining(String title); // 게시물 제목을 포함하는 게시물 리스트 검색 (회원 게시물만)

    Page<Board> findByIsDeletedFalse(Pageable pageable); // 삭제되지 않은 게시물을 페이지로 가져옴

    // 데이터베이스 서버의 현재 시각 가져오기
    @Query(value = "SELECT NOW()", nativeQuery = true)
    String getTime();
    Page<Board> findAllByOrderByModDateDesc(Pageable pageable);

}