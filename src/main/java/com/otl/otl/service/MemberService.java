package com.otl.otl.service;

import com.otl.otl.domain.Member;

public interface MemberService {
    Member registerOrUpdateMember(String nickname, String email, String profileImage);
    void deleteByEmail(String email);

    String findNicknameByEmail(String email); //이메일로 닉네임 가져오기

    Member findByEmail(String email);
    void save(Member member);  // 업데이트 로직
}
