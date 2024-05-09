package com.otl.otl.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Long mno;               // 회원 고유번호

    private String email;         // 회원 이메일

    private String nickname;           // 회원 이름 (닉네임)

    private String memberProfileImage;     // 회원 프로필 사진

    private String memberDescription; // 회원 자기 소개

    @Override
    public String toString() {
        return "MemberDTO{" +
                "nickname='" + nickname + '\'' +
                ", memberProfileImage='" + memberProfileImage + '\'' +
                '}';
    }
}