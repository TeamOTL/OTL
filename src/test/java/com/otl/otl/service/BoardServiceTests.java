package com.otl.otl.service;

import com.otl.otl.domain.Member;
import com.otl.otl.dto.BoardDTO;
import com.otl.otl.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testRegister() {

        log.info(boardService.getClass().getName());

        // 이메일 주소로 회원 조회
        Optional<Member> optionalMember = memberRepository.findByEmail("example3@example.com");

        // 회원이 존재할 경우에만 이메일 주소를 member 변수에 할당
        Member member = optionalMember.orElse(null);

        // BoardDTO 생성 및 데이터 설정
        BoardDTO boardDTO = BoardDTO.builder()
                .boardTitle("Sample Title...")
                .boardContent("Sample Content...")
                .member(member) // 회원 객체 할당
                .build();

        // 게시글 저장
        Long board_id = boardService.register(boardDTO);
        log.info("board_id: " + board_id);
    }

    @Test
    public void testRead(){

        BoardDTO boardDTO = boardService.readOne(24L);

        log.info(boardDTO);
    }

    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(3)
                .boardTitle("Updated....3")
                .boardContent("Updated content 3...")
                .build();

        boardService.modify(boardDTO);
    }

    @Test
    public void testDelete(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(13)
                .build();

        boardService.remove(boardDTO);
    }
}
