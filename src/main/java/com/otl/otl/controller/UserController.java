package com.otl.otl.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Map;

@Controller
public class UserController {

    @GetMapping("/main")
    public String getUserInfo(@AuthenticationPrincipal OAuth2User oauthUser, Model model) {
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        model.addAttribute("name", profile.get("nickname"));
        model.addAttribute("email", kakaoAccount.get("email"));
        model.addAttribute("profileImage", profile.get("profile_image_url"));

        return "main";  // main.html 템플릿을 반환
    }

}
