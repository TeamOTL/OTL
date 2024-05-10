package com.otl.otl.repository;

import com.otl.otl.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email); // 이메일로 회원을 찾는 메소드 추가



    // 이메일을 이용해 회원 데이터를 삭제하는 메소드
    @Transactional
    void deleteByEmail(String email);
}
