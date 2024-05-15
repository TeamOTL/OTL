package com.otl.otl.service;

import com.otl.otl.domain.Reply;
import com.otl.otl.dto.BoardDTO;
import com.otl.otl.dto.ReplyDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO); // 등록
    ReplyDTO readOne(Long replyNo); // 조회
    void remove(Long replyNo); // 삭제

    List<ReplyDTO>findRepliesByBno(Long bno);

}
