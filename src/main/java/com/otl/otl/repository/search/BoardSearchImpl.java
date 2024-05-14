package com.otl.otl.repository.search;

import com.otl.otl.domain.Board;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import com.otl.otl.domain.QBoard;

import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {

        super(Board.class);
    }

    @Override
    public Page<Board> findByIsDeletedFalse(Pageable pageable) {
        return null;
    }

}