package com.otl.otl.repository;

import com.otl.otl.domain.Board;
import com.otl.otl.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Page<Board> findByOrderByModDateDesc(Pageable pageable);

    List<Reply> findByBoard_Bno(Long bno);
}
