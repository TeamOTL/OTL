package com.otl.otl.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class DashBoardController {

//    @ApiOperation(value = "대시보드 조회", notes = "대시보드를 조회합니다.")
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal OAuth2User oauthUser, Model model) {

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
        return "dashBoard";
    }
}
