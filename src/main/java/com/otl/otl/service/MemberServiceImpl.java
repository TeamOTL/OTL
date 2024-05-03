package com.otl.otl.service;

import com.otl.otl.domain.Member;
import com.otl.otl.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member registerOrUpdateMember(String nickname, String email, String profileImage){
        Member member = memberRepository.findByEmail(email)
                .orElse(new Member()); // 이메일로 기존 회원 조회, 없으면 새 객체 생성

        member.setNickname(nickname);
        member.setEmail(email);
        member.setMemberProfileImage(profileImage);

        return memberRepository.save(member); // 데이터베이스에 저장
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {

        memberRepository.deleteByEmail(email);
        System.out.println(email + " 사용자가 시스템에서 삭제되었습니다."); // 실제 구현 시 삭제
    }
}
