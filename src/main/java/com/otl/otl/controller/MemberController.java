package com.otl.otl.controller;

import com.otl.otl.domain.Member;
import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.*;
import com.otl.otl.repository.MemberRepository;
import com.otl.otl.repository.MemberStudyRepository;
import com.otl.otl.service.BoardService;
import com.otl.otl.service.MemberService;
import com.otl.otl.service.StudyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@Log4j2
public class MemberController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final StudyService studyService;
    private final MemberRepository memberRepository;
    private final MemberStudyRepository memberStudyRepository;

    @Autowired
    public MemberController(MemberService memberService, BoardService boardService, StudyService studyService, MemberRepository memberRepository, MemberStudyRepository memberStudyRepository) {
        this.memberService = memberService;
        this.boardService = boardService;
        this.studyService = studyService;
        this.memberRepository = memberRepository;
        this.memberStudyRepository = memberStudyRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/studyRoom_yu/{sno}")
    public String studyRoom_yu(@PathVariable Long sno, @AuthenticationPrincipal OAuth2User oauthUser, Model model) {
        if (oauthUser == null) {
            log.error("OAuth2 사용자 정보가 없습니다.");
            return "redirect:/";
        }
        Map<String, Object> attributes = oauthUser.getAttributes();
        log.info("OAuth2 사용자 속성: {}", attributes);

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        String email = (String) kakaoAccount.get("email");
        if (email == null) {
            log.error("이메일 속성이 없습니다.");
            return "redirect:/";
        }

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Optional<StudyDTO> optionalStudyDTO = studyService.findStudyById(sno);

        if (optionalStudyDTO.isEmpty()) {
            log.error("스터디를 찾을 수 없습니다. 스터디 ID: {}", sno);
            return "redirect:/error"; // 오류 페이지로 리다이렉트
        }

        StudyDTO studyDTO = optionalStudyDTO.get();
        List<MemberDTO> memberDTOs = studyDTO.getMemberNicknames().stream()
                .map(nickname -> {
                    Member studyMember = memberRepository.findByNickname(nickname)
                            .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다. 닉네임: " + nickname));
                    return new MemberDTO(studyMember.getEmail(), studyMember.getNickname(), studyMember.getMemberProfileImage());
                })
                .collect(Collectors.toList());

        studyDTO.setMembers(memberDTOs);

        model.addAttribute("study", studyDTO);
        model.addAttribute("currentStudyId", sno);
        model.addAttribute("isManager", studyService.isManager(sno, email));

        log.info("스터디 정보를 모델에 추가했습니다. sno: {}, studyDTO: {}", sno, studyDTO);

        return "studyRoom_yu";  // studyRoom_yu.html 템플릿을 반환
    }





    @GetMapping("/dashBoard")
    public String getUserInfo(@AuthenticationPrincipal OAuth2User oauthUser, Model model) {
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String nickname = (String) profile.get("nickname");
        String email = (String) kakaoAccount.get("email");
        String memberProfileImage = (String) profile.get("profile_image_url");

        // memberService.registerOrUpdateMember 메소드가 Member 객체를 반환하도록 하고, 해당 객체를 사용합니다.
        Member member = memberService.registerOrUpdateMember(nickname, email, memberProfileImage);

        // 모델에 사용자 정보 추가
        model.addAttribute("nickname", nickname);
        model.addAttribute("email", email);
        model.addAttribute("memberProfileImage", member.getMemberProfileImage());

        return "dashBoard";  // dashBoard.html 템플릿을 반환
    }

    @Operation(summary = "제목", description = "내용")
    @PostMapping("/delete-account")
    public String deleteAccount(@AuthenticationPrincipal OAuth2User oauthUser) {
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        String email = (String) kakaoAccount.get("email"); // OAuth2User에서 이메일 추출

        if (email != null) { // 이메일이 null이 아닌 경우에만 계정 삭제 시도
            memberService.deleteByEmail(email); // 이메일을 사용해 계정 삭제 서비스 호출
            log.info(email + " 사용자 계정 삭제 처리됨");
        } else {
            log.error("OAuth2User로부터 이메일을 추출할 수 없습니다.");
        }
        // 로그아웃 처리 후 홈으로 리다이렉션
        return "redirect:/";
    }

    @GetMapping("/board")
    public String board(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @AuthenticationPrincipal OAuth2User oauthUser,
                        Model model,
                        @RequestParam(required = false) String type,
                        @RequestParam(required = false) String keyword) {
        log.info("게시판 페이지 요청 - 페이지 번호: {}, 페이지 크기: {}", page, size);

        // 게시글 목록 조회 및 모델에 추가
        Page<BoardDTO> boardPage;
        // 일반 요청일 경우
        boardPage = boardService.findBoards(page, size);
        log.info("게시판 목록 요청 - 페이지 번호: {}, 페이지 크기: {}", page, size);

        model.addAttribute("boards", boardPage.getContent());
        model.addAttribute("currentPage", boardPage.getNumber());
        model.addAttribute("totalPages", boardPage.getTotalPages());


        if (type != null && keyword != null && !type.isEmpty() && !keyword.isEmpty()) {
            // 검색 요청일 경우
            boardPage = boardService.searchBoards(type, keyword, page, size);
            log.info("게시판 검색 요청 - 페이지 번호: {}, 페이지 크기: {}", page, size);

            model.addAttribute("boards", boardPage.getContent());
            model.addAttribute("currentPage", boardPage.getNumber());
            model.addAttribute("totalPages", boardPage.getTotalPages());

            model.addAttribute("type", type);
            model.addAttribute("keyword", keyword);
        } else {
            // 일반 요청일 경우
            boardPage = boardService.findBoards(page, size);
            log.info("게시판 목록 요청 - 페이지 번호: {}, 페이지 크기: {}", page, size);

            model.addAttribute("boards", boardPage.getContent());
            model.addAttribute("currentPage", boardPage.getNumber());
            model.addAttribute("totalPages", boardPage.getTotalPages());

        }
//        model.addAttribute("boards", boardPage.getContent());
//        model.addAttribute("currentPage", boardPage.getNumber());
//        model.addAttribute("totalPages", boardPage.getTotalPages());


        if (oauthUser != null) {
            Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            String nickname = (String) profile.get("nickname");
            String email = (String) kakaoAccount.get("email");
            String memberProfileImage = (String) profile.get("profile_image_url");

            model.addAttribute("nickname", nickname);
            model.addAttribute("email", email);
            model.addAttribute("memberProfileImage", memberProfileImage);
        } else {
            // 세션에 사용자 정보가 없을 경우 로그인 페이지로 리다이렉트
            return "redirect:/";
        }

        return "board"; // board.html 템플릿을 반환
    }

    @GetMapping("/api/user")
    @ResponseBody
    public Map<String, String> getUserInfo(@AuthenticationPrincipal OAuth2User oauthUser) {
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String nickname = (String) profile.get("nickname");
        String email = (String) kakaoAccount.get("email");
        String memberProfileImage = (String) profile.get("profile_image_url");

        Member member = memberService.findByEmail(email); // 회원 정보 조회
        String memberDescription = (member != null) ? member.getMemberDescription() : "";

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("nickname", nickname);
        userInfo.put("email", email);
        userInfo.put("profileImage", memberProfileImage);
        userInfo.put("memberDescription", memberDescription);

        return userInfo;
    }

    @PutMapping("/api/user")
    @ResponseBody
    public ResponseEntity<Map<String, String>> updateUserInfo(@RequestBody Map<String, String> userInfo, @AuthenticationPrincipal OAuth2User oauthUser) {
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        String email = (String) kakaoAccount.get("email");

        Member member = memberService.findByEmail(email);
        if (member != null) {
            member.setNickname(userInfo.get("nickname"));
            member.setMemberDescription(userInfo.get("memberDescription"));

            memberService.save(member);
            return ResponseEntity.ok(Map.of("message", "유저 정보를 성공적으로 변경했습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "유저를 찾을 수 없습니다."));
        }
    }

    @GetMapping("/studyJoin")
    public String studyJoin(@AuthenticationPrincipal OAuth2User oauthUser, Model model) {
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String nickname = (String) profile.get("nickname");
        String email = (String) kakaoAccount.get("email");
        String memberProfileImage = (String) profile.get("profile_image_url");

        // memberService.registerOrUpdateMember 메소드가 Member 객체를 반환하도록 하고, 해당 객체를 사용합니다.
        Member member = memberService.registerOrUpdateMember(nickname, email, memberProfileImage);

        // 모델에 사용자 정보 추가
        model.addAttribute("nickname", nickname);
        model.addAttribute("email", email);
        model.addAttribute("memberProfileImage", member.getMemberProfileImage());

        return "studyJoin";  // studyJoin.html 템플릿을 반환
    }

    // 스터디룸 관리 페이지의 데이터를 가져오는 API
    @GetMapping("/studyRoomManager/{sno}")
    public String getStudyForManager(@PathVariable Long sno, @AuthenticationPrincipal OAuth2User oauthUser, Model model) {
        log.info("스터디룸 관리 페이지 요청 - 스터디 ID: {}", sno);
        if (oauthUser == null) {
            log.error("OAuth2User is null");
            return "redirect:/"; // 로그인 페이지로 리다이렉트
        }
        Map<String, Object> attributes = oauthUser.getAttributes();
        log.info("OAuth2User Attributes: {}", attributes);

        // 카카오 로그인에서 이메일 속성 가져오기
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        String email = (String) kakaoAccount.get("email");
        if (email == null) {
            log.error("Email attribute is null");
            return "redirect:/"; // 로그인 페이지로 리다이렉트
        }

        // 스터디 ID로 스터디 조회
        Optional<StudyDTO> optionalStudyDTO = studyService.findStudyById(sno);
        if (optionalStudyDTO.isEmpty() || !studyService.isManager(sno, email)) {
            log.error("스터디룸을 찾을 수 없거나 권한이 없습니다 - 스터디 ID: {}", sno);
            return "error/403"; // 권한이 없으면 403 페이지로 이동
        }

        StudyDTO studyDTO = optionalStudyDTO.get();
        List<MemberDTO> members = studyService.getStudyMembers(sno);
        List<MemberDTO> applicants = studyService.getStudyApplicants(sno);

        model.addAttribute("study", studyDTO);
        model.addAttribute("members", members);
        model.addAttribute("applicants", applicants);
        model.addAttribute("isManager", true);

        log.info("스터디룸 관리 페이지 응답 - 스터디 ID: {}", sno);
        return "studyRoomManager"; // studyroommanager.html 템플릿으로 이동
    }
}
