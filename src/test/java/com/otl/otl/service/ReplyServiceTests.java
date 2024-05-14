package com.otl.otl.service;

import com.otl.otl.domain.Reply;
import com.otl.otl.dto.ReplyDTO;
import com.otl.otl.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private ReplyRepository replyRepository;
    @Test
    public void testRegister() {

        // ReplyDTO 생성 및 데이터 설정
        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyContent("ReplyDTO Test2")
                .email("youjio2000@gmail.com")
                .bno(3L)
                .build();

        log.info(replyService.register(replyDTO));
    }

    @Test
    public void testRead(){

        // 댓글 조회
        ReplyDTO replyDTO = replyService.readOne(4L);

        log.info(replyDTO);
    }

    @Test
    public void testDelete(){

        // 댓글 삭제
        replyService.remove(3L);

        Optional<Reply> deletedReply = replyRepository.findById(3L);
        deletedReply.ifPresent(reply -> log.info("댓글 삭제 중 에러가 발생했습니다."));

    }
}
