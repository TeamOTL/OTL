package com.otl.otl.service;

import com.otl.otl.repository.BoardRepository;
import com.otl.otl.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.otl.otl.domain.Board;
import com.otl.otl.domain.Member;
import com.otl.otl.dto.BoardDTO;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private  final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ModelMapper modelMapper;


    @Override
    public Long register(BoardDTO boardDTO) {
        // BoardDTO에서 필요한 정보를 추출하여 Board 객체로 변환합니다.
        Optional<Member> optionalMember = memberRepository.findByEmail(boardDTO.getEmail());
        Member member = optionalMember.orElse(null); // Optional이 비어있으면 null을 반환
        if (member == null) {
            // 해당 이메일로 등록된 회원이 없으면 게시글 등록에 실패합니다.
            log.error("회원 등록 실패: 이메일 {} 에 해당하는 회원이 없습니다.", boardDTO.getEmail());
            return null;
        }

        // BoardDTO를 Board 엔티티로 변환
        Board board = Board.builder()
                .boardTitle(boardDTO.getBoardTitle())
                .boardContent(boardDTO.getBoardContent())
                .member(member)
                .isDeleted(false) // 초기값 설정
                .build();

        // 생성된 Board 객체를 저장하고, 저장된 게시글의 ID를 반환합니다.
        Board savedBoard = boardRepository.save(board);
        log.info("새로운 게시글이 등록되었습니다: 게시글 ID {}", savedBoard.getBno());
        return savedBoard.getBno();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BoardDTO> findBoards(int page, int size) {
        log.info("게시글 목록 조회: 페이지 번호 {}, 페이지 크기 {}", page, size);

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Board> boardPage = boardRepository.findAllByOrderByModDateDesc(pageRequest);

        return boardPage.map(this::entityToDto);
    }

    // Board 엔티티를 BoardDTO로 변환합니다.
    private BoardDTO entityToDto(Board board) {
        String email = board.getMember() != null ? board.getMember().getEmail() : null;
        String nickname = (email != null) ? memberService.findNicknameByEmail(email) : null;

        return BoardDTO.builder()
                .bno(board.getBno()) // 게시글 번호
                .boardTitle(board.getBoardTitle()) // 제목
                .boardContent(board.getBoardContent()) // 내용
                .email(email) // 회원 이메일 처리
                .nickname(nickname) // 회원 닉네임 처리
                .isDeleted(board.isDeleted()) // 삭제 여부
                .regDate(board.getRegDate()) // 등록 날짜
                .modDate(board.getModDate()) // 수정 날짜
                .build();
    }

    public BoardDTO readOne(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);

        return boardDTO;
    }

    @Override
    public Board modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());

        Board board = result.orElseThrow();

        board.change(boardDTO.getBoardTitle(), boardDTO.getBoardContent());

        return board;
    }

//    @Override
//    public void remove(BoardDTO boardDTO) {
//        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
//
//        Board board = result.orElseThrow();
//
//        board.isDeleted(true); // 삭제 처리
//    }

    @Override
    @Transactional
    public void remove(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. bno=" + bno));
        board.setDeleted(true); // 삭제 처리
    }

}