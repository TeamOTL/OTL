package com.otl.otl.controller;

import com.otl.otl.domain.Member;
import com.otl.otl.service.MemberService;


import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@Controller
@Log4j2
//@RestController
public class MemberController {

    private final MemberService  memberService;

    public MemberController(MemberService  memberService){
        this.memberService  = memberService;
    }

    @GetMapping("/")
    public  String index(){
        return "index";
    }
//    @ApiOperation(value = "title POTS/GET", notes = "내용")
    @GetMapping("/main")
    public String getUserInfo(@AuthenticationPrincipal OAuth2User oauthUser, Model model) {
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String nickname = (String) profile.get("nickname");
        String email = (String) kakaoAccount.get("email");
        String memberProfileImage = (String) profile.get("profile_image_url");

        // memberService.registerOrUpdateMember 메소드가 Member 객체를 반환하도록 하고, 해당 객체를 사용합니다.
        Member member = memberService.registerOrUpdateMember(nickname, email, memberProfileImage);

        //모델에 사용자 정보 추가
        model.addAttribute("nickname", nickname);
        model.addAttribute("email", email);
        model.addAttribute("memberProfileImage", memberProfileImage);



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

    @GetMapping("/board")
    public String board(@AuthenticationPrincipal OAuth2User oauthUser, Model model){


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



}
