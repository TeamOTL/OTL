package com.otl.otl.service;

import com.otl.otl.domain.Member;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MemberService {
    Member registerOrUpdateMember(String nickname, String email, String profileImage);
    void deleteByEmail(String email);

    String findNicknameByEmail(String email); //이메일로 닉네임 가져오기

    Member findByEmail(String email);
    void save(Member member);  // 업데이트 로직
    void updateProfileImage(Long memberId, MultipartFile file) throws IOException;
}
