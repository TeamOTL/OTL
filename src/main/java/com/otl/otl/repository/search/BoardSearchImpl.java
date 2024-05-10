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
    public List<Board> findByBoardTitleContaining(String keyword) {
        return null;
    }

    @Override
    public Page<Board> findByIsDeletedFalse(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Board> search1(Pageable pageable) {
        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

        booleanBuilder.or(board.boardTitle.contains("11")); // title like ...

        booleanBuilder.or(board.boardContent.contains("11")); // content like ....

        query.where(booleanBuilder);
        query.where(board.bno.gt(0L));


        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if ((types != null && types.length > 0) && keyword != null) { //검색 조건과 키워드가 있다면

            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

            for (String type : types) {

                switch (type) {
                    case "t":
                        booleanBuilder.or(board.boardTitle.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.boardContent.contains(keyword));
                        break;
                    // ,"w"는 닉네임으로 받을 예정
//                    case "w":
//                        booleanBuilder.or(board.email.contains(keyword));
//                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        //bno > 0
        query.where(board.bno.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);

    }
}