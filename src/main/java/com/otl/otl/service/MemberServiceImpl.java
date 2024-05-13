package com.otl.otl.service;

import com.otl.otl.domain.Member;
import com.otl.otl.repository.MemberRepository;
import groovy.util.logging.Log4j2;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member registerOrUpdateMember(String nickname, String email, String profileImage){
        Member member = memberRepository.findByEmail(email).orElse(null);

        if (member == null) {
            member = new Member();
            member.setEmail(email);
            member.setNickname(nickname);


            if (profileImage != null && !profileImage.isEmpty()) {

                member.setMemberProfileImage(profileImage.getBytes());  // 초기에는 카카오 프로필 URL을 그대로 저장
            }
        } else {
            member.setNickname(nickname);


            if (profileImage != null && !profileImage.isEmpty() && profileImage.startsWith("data:image")) {

                try {
                    String[] parts = profileImage.split(",");
                    if (parts.length == 2) {
                        byte[] imageBytes = Base64.getDecoder().decode(parts[1]);
                        member.setMemberProfileImage(imageBytes);
                    } else {
                        throw new IllegalArgumentException("잘못된 프로필 이미지 형식입니다");
                    }
                } catch (IllegalArgumentException e) {
                    log.error("프로필 이미지 디코딩 오류", e);
                    throw e;
                }
            }
            }

        return memberRepository.save(member);
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {

        memberRepository.deleteByEmail(email);
        System.out.println(email + " 사용자가 시스템에서 삭제되었습니다."); // 실제 구현 시 삭제
    }

    @Override
    public String findNicknameByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.map(Member::getNickname).orElse("알 수 없음");
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElse(null);
    }

    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }
}
