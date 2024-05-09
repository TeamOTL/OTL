//package com.otl.otl.service;
//
//import com.otl.otl.domain.Board;
//import com.otl.otl.domain.Member;
//import com.otl.otl.dto.BoardDTO;
//import com.otl.otl.dto.MemberDTO;
//import com.otl.otl.repository.MemberRepository;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//@SpringBootTest
//@Log4j2
//public class BoardServiceTests {
//    @Autowired
//    private BoardService boardService;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test
//    public void testRegister() {
//
//        log.info(boardService.getClass().getName());
//
//        // 현재 로그인한 회원의 이메일 주소 (예: youjio2000@gmail.com)
//        String loggedInEmail = "youjio2000@gmail.com";
//
//        // 로그인한 이메일 주소로 회원 조회
//        Optional<Member> optionalMember = memberRepository.findByEmail(loggedInEmail);
//
//        // member 테이블에 없는 이메일 주소로는 게시글 저장 안됨
//        //Optional<Member> optionalMember = memberRepository.findByEmail("test@gmail.com");
//
//        // 회원이 존재할 경우에만 이메일 주소를 email 변수에 할당
//        String email = optionalMember.map(Member::getEmail).orElse(null);
//
//        // BoardDTO 생성 및 데이터 설정
//        BoardDTO boardDTO = BoardDTO.builder()
//                .boardTitle("Sample Title...4")
//                .boardContent("Sample Content...4")
//                .email(email) // 회원 객체 할당
//                .build();
//
//        // 게시글 저장
//        Long bno = boardService.register(boardDTO);
//        log.info("bno: " + bno);
//    }
//
//    @Test
//    public void testRead(){
//
//        // 게시글 조회
//        BoardDTO boardDTO = boardService.readOne(24L);
//
//        // 이메일 주소로 회원 조회
//        Optional<Member> optionalMember = memberRepository.findByEmail(boardDTO.getEmail());
//
//        MemberDTO memberDTO = new MemberDTO();
//        optionalMember.ifPresent(member -> {
//            // 회원 정보 가져와서 MemberDTO에 추가
//            memberDTO.setNickname(member.getNickname());
//            memberDTO.setMemberProfileImage(member.getMemberProfileImage());
//        });
//
//        // 게시글과 회원 정보를 합쳐서 로그에 출력
//        log.info(boardDTO.toString() + " " + memberDTO.toString());
//    }
//
//    @Test
//    public void testModify(){
//
//        // 현재 로그인한 회원의 이메일 주소 (예: youjio2000@gmail.com)
//        String loggedInEmail = "youjio2000@gmail.com";
//
//        // 수정하려고 하는 게시글 bno
//        long modifiedBno = 24L;
//
//        // 게시글을 작성한 회원의 이메일 주소 확인
//        BoardDTO boardDTO = boardService.readOne(modifiedBno);
//
//        // 로그인한 이메일 주소와 게시글을 작성한 회원의 이메일 주소가 일치하는지 확인
//        if(loggedInEmail.equals(boardDTO.getEmail())){
//            Optional<Member> optionalMember = memberRepository.findByEmail(loggedInEmail);
//
//            // 회원이 존재할 때만 수정
//            if (optionalMember.isPresent()) {
//                BoardDTO modifiedBoardDTO = BoardDTO.builder()
//                        .bno(modifiedBno)
//                        .boardTitle("Updated....2")
//                        .boardContent("Updated content 2...")
//                        .build();
//
//                Board result = boardService.modify(modifiedBoardDTO);
//                log.info("bno: " + result.getBno());
//            } else {
//                log.warn("회원 정보를 찾을 수 없습니다.");
//            }
//        } else {
//            log.warn("게시글 작성자와 일치하지 않습니다.");
//        }
//    }
//
//    @Test
//    public void testDelete(){
//
//        // 현재 로그인한 회원의 이메일 주소 (예: youjio2000@gmail.com)
//        String loggedInEmail = "youjio2000@gmail.com";
//
//        // 삭제하려고 하는 게시글 bno
//        long deletedBno = 25L;
//
//        // 게시글을 작성한 회원의 이메일 주소 확인
//        BoardDTO boardDTO = boardService.readOne(deletedBno);
//
//        // 로그인한 이메일 주소와 게시글을 작성한 회원의 이메일 주소가 일치하는지 확인
//        if (loggedInEmail.equals(boardDTO.getEmail())) {
//            // 게시글 삭제
//            boardService.remove(deletedBno);
//            log.info("삭제되었습니다.");
//        } else {
//            log.warn("게시글 작성자와 일치하지 않습니다.");
//        }
//    }
//}