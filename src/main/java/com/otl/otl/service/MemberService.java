package com.otl.otl.service;

import com.otl.otl.domain.Member;

public interface MemberService {
    Member registerOrUpdateMember(String nickname, String email, String profileImage);
    void deleteByEmail(String email);
}
