package com.otl.otl.service;

import com.otl.otl.repository.BoardRepository;
import com.otl.otl.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.otl.otl.domain.Board;
import com.otl.otl.domain.Member;
import com.otl.otl.dto.BoardDTO;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@Transactional
public class BoardServiceImpl implements BoardService{


    private final BoardRepository boardRepository;
    private  final MemberRepository memberRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;

    }

    @Override
    public Long register(BoardDTO boardDTO) {
        // BoardDTO에서 필요한 정보를 추출하여 Board 객체로 변환합니다.
        Optional<Member> optionalMember = memberRepository.findByEmail(boardDTO.getEmail());
        Member member = optionalMember.orElse(null); // Optional이 비어있으면 null을 반환
        if (member == null) {
            // 해당 이메일로 등록된 회원이 없으면 게시글 등록에 실패합니다.
            return null;
        }

        Board board = Board.builder()
                .boardTitle(boardDTO.getBoardTitle())
                .boardContent(boardDTO.getBoardContent())
                .member(member)
                .build();

        // 생성된 Board 객체를 저장하고, 저장된 게시글의 ID를 반환합니다.
        return boardRepository.save(board).getBno();
    }
}