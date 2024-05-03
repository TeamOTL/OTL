package com.otl.otl.controller;

import com.otl.otl.domain.Member;
import com.otl.otl.service.MemberService;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Map;

@Controller
@Log4j2
public class MemberController {

    private final MemberService  memberService;

    public MemberController(MemberService  memberService){
        this.memberService  = memberService;
    }

    @GetMapping("/main")
    public String getUserInfo(@AuthenticationPrincipal OAuth2User oauthUser, Model model) {
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String nickname = (String) profile.get("nickname");
        String email = (String) kakaoAccount.get("email");
        String profileImage = (String) profile.get("profile_image_url");

        memberService.registerOrUpdateMember(nickname, email, profileImage);

        model.addAttribute("name", nickname);
        model.addAttribute("email", email);
        model.addAttribute("profileImage", profileImage);

        return "main";  // main.html 템플릿을 반환
    }

    @PostMapping("/delete-account")
    public String deleteAccount(@AuthenticationPrincipal OAuth2User  oauthUser) {
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        String email = (String) kakaoAccount.get("email"); //  OAuth2User에서 이메일 추출

        if (email != null) { // 이메일이 null이 아닌 경우에만 계정 삭제 시도
            memberService.deleteByEmail(email); // 이메일을 사용해 계정 삭제 서비스 호출
            log.info(email + " 사용자 계정 삭제 처리됨");
        } else {
            log.error("OAuth2User로부터 이메일을 추출할 수 없습니다.");
        }
        // 로그아웃 처리 후 홈으로 리다이렉션
        return "redirect:/";

    }

}
