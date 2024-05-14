package com.otl.otl.service;

import com.otl.otl.domain.Member;
import com.otl.otl.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member registerOrUpdateMember(String nickname, String email, String profileImage) {
        Member member = memberRepository.findByEmail(email).orElse(null);

        if (member == null) {
            member = new Member();
            member.setEmail(email);
            member.setNickname(nickname);

            if (profileImage != null && !profileImage.isEmpty()) {
                member.setMemberProfileImage(profileImage);  // 초기에는 카카오 프로필 URL을 그대로 저장
            }
        } else {
            member.setNickname(nickname);

            if (profileImage != null && !profileImage.isEmpty()) {
                member.setMemberProfileImage(profileImage);  // 업데이트 시에도 URL을 그대로 저장
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

    @Override
    public void updateProfileImage(Long memberId, MultipartFile file) {
        throw new UnsupportedOperationException("프로필 이미지 변경 기능이 지원되지 않습니다.");
    }
}
