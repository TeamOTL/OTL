package com.otl.otl.controller;

import com.otl.otl.domain.Member;
import com.otl.otl.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Map;

@Controller
public class UserController {

    private final MemberService memberService;

    public UserController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/main")
    public String getUserInfo(@AuthenticationPrincipal OAuth2User oauthUser, Model model) {
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String nickname = (String) profile.get("nickname");
        String email = (String) kakaoAccount.get("email");
        String profileImage = (String) profile.get("profile_image_url");

        Member member = memberService.registerOrUpdateMember(nickname, email, profileImage);

        model.addAttribute("name", profile.get("nickname"));
        model.addAttribute("email", kakaoAccount.get("email"));
        model.addAttribute("profileImage", profile.get("profile_image_url"));

        return "main";  // main.html 템플릿을 반환
    }

}
