package com.otl.otl.service;

import com.otl.otl.domain.Board;
import com.otl.otl.domain.Member;
import com.otl.otl.domain.Reply;
import com.otl.otl.dto.BoardDTO;
import com.otl.otl.dto.ReplyDTO;
import com.otl.otl.repository.BoardRepository;
import com.otl.otl.repository.MemberRepository;
import com.otl.otl.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ReplyServiceImpl implements ReplyService{

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ReplyRepository replyRepository;
    private final MemberService memberService;
    private final ModelMapper modelMapper;
    @Override
    public Long register(ReplyDTO replyDTO) {
        // 회원 이메일로 Member 엔티티 조회
        Optional<Member> optionalMember = memberRepository.findByEmail(replyDTO.getEmail());
        Member member = optionalMember.orElseThrow(() -> new IllegalArgumentException("해당 이메일의 회원이 존재하지 않습니다: " + replyDTO.getEmail()));

        // 게시글 번호로 Board 엔티티 조회
        Optional<Board> optionalBoard = boardRepository.findById(replyDTO.getBno());
        Board board = optionalBoard.orElseThrow(() -> new IllegalArgumentException("해당 번호의 게시글이 존재하지 않습니다: " + replyDTO.getBno()));

        // ReplyDTO를 Reply 엔티티로 변환
        Reply reply = Reply.builder()
                .replyContent(replyDTO.getReplyContent())
                .board(board)
                .member(member)
                //.isDeleted(false)
                .build();

        // 생성된 Board 객체를 저장하고, 저장된 게시글의 ID를 반환합니다.
        Reply savedReply = replyRepository.save(reply);
        log.info("새로운 게시글이 등록되었습니다: 게시글 ID {}", savedReply.getReplyNo());
        return savedReply.getReplyNo();
    }

    @Override
    public ReplyDTO readOne(Long replyNo) {
        Optional<Reply> result = replyRepository.findById(replyNo);

        Reply reply = result.orElseThrow();

        ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);

        return replyDTO;
    }

    @Override
    public void remove(Long replyNo) {
        if (replyNo != null) {
            Optional<Reply> result = replyRepository.findById(replyNo);
            result.ifPresent(reply -> replyRepository.delete(reply)); // 삭제 처리
        }
    }

    @Override
    public Page<ReplyDTO> findRepliesByBno(Long bno, int page, int size) {
        log.info("댓글 목록 조회 - 게시글 번호: {}, 페이지 번호: {}, 페이지 크기: {}", bno, page, size);

        // 해당 게시글 번호에 해당하는 댓글 목록을 데이터베이스에서 가져옵니다.
        Pageable pageable = PageRequest.of(page, size);
        Page<Reply> replyPage = replyRepository.findByBoard_Bno(bno, pageable);

        // 가져온 댓글 목록을 ReplyDTO로 변환합니다.
        List<ReplyDTO> replyDTOList = replyPage.getContent().stream()
                .map(reply -> modelMapper.map(reply, ReplyDTO.class))
                .collect(Collectors.toList());

        return replyPage.map(this::entityToDto);
    }

//    @Override
//    public Page<ReplyDTO> findReplyies(int page, int size) {
//        log.info("댓글 목록 조회: 페이지 번호 {}, 페이지 크기 {}", page, size);
//
//        // 댓글 조회
//        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "modDate"));
//        Page<Reply> replyPage = replyRepository.findAll(pageRequest);
//        return replyPage.map(this::entityToDto);
//    }

    // Reply 엔티티를 ReplyDTO로 변환하는 메서드
    private ReplyDTO entityToDto(Reply reply) {
        String email = reply.getMember() != null ? reply.getMember().getEmail() : null;
        return ReplyDTO.builder()
                .replyNo(reply.getReplyNo()) // 댓글 번호
                .replyContent(reply.getReplyContent()) // 내용
                .email(email) // 회원 이메일 처리
                .build();
    }

}
