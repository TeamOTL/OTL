package com.otl.otl.controller;

import com.otl.otl.dto.BoardDTO;
import com.otl.otl.dto.ReplyDTO;
import com.otl.otl.repository.ReplyRepository;
import com.otl.otl.service.MemberService;
import com.otl.otl.service.ReplyService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Validated // DTO 유효성 검사를 위한 어노테이션
@Log4j2
public class ReplyController {

    private final ReplyService replyService;
    private final ReplyRepository replyRepository;
    private final MemberService memberService;

    public ReplyController(ReplyService replyService, ReplyRepository replyRepository, MemberService memberService) {
        this.replyService = replyService;
        this.replyRepository = replyRepository;
        this.memberService = memberService;
    }

    @PostMapping("/saveReply")
    public ResponseEntity<String> saveReply(@Valid @RequestBody ReplyDTO replyDTO) {

        // 댓글 저장 시도 로깅
        log.info("댓글 저장 시도 : {}", replyDTO);

        Long replyNo = replyService.register(replyDTO);

        // 저장 결과에 따라 적절한 응답을 반환합니다.
        if (replyNo != null) {
            log.info("댓글이 성공적으로 저장되었습니다. 댓글 번호: {}", replyNo);
            return ResponseEntity.status(HttpStatus.CREATED).body("댓글이 성공적으로 등록되었습니다.");
        } else {
            log.info("댓글 저장 실패: {}", replyDTO);
            return ResponseEntity.badRequest().body("댓글 등록에 실패했습니다.");
        }
    }

    @GetMapping("/replyList")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> replyList(@RequestParam Long bno) {
        log.info("댓글 요청 - 게시글 번호: {}", bno);

        // 특정 게시글 번호에 해당하는 댓글 목록 조회
        List<ReplyDTO> replyList = replyService.findRepliesByBno(bno);

        // 댓글 목록을 담을 리스트 생성
        List<Map<String, Object>> formattedReplyList = new ArrayList<>();

        // 각 댓글의 정보를 담아서 리스트에 추가
        for (ReplyDTO replyDTO : replyList) {
            Map<String, Object> replyInfo = new HashMap<>();
            replyInfo.put("replyNo", replyDTO.getReplyNo());
            replyInfo.put("email", replyDTO.getEmail());
            replyInfo.put("nickname", memberService.findNicknameByEmail(replyDTO.getEmail()));
            replyInfo.put("replyContent", replyDTO.getReplyContent());
            formattedReplyList.add(replyInfo);
        }

        // JSON 데이터를 담을 Map 생성
        Map<String, Object> response = new HashMap<>();
        response.put("content", formattedReplyList); // 댓글 목록

        return ResponseEntity.ok(response);
    }

    @PostMapping("/deleteReply")
    public ResponseEntity<Void> deleteReply(@RequestBody ReplyDTO replyDTO) {
        // 클라이언트에서 보낸 삭제 예정인 댓글의 번호를 가져옴
        Long replyNo = replyDTO.getReplyNo();

        // 그냥 댓글 삭제

        // 게시글 삭제 시도 로깅
        log.info("댓글 삭제 시도 : {}", replyNo);

        replyService.remove(replyNo);

        log.info("댓글이 성공적으로 삭제되었습니다. 댓글 번호: {}", replyNo);

        // 수정된 데이터를 클라이언트에게 반환
        return ResponseEntity.ok().build();
    }
}
