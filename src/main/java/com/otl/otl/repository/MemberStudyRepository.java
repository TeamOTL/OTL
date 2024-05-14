package com.otl.otl.repository;

import com.otl.otl.domain.Member;
import com.otl.otl.domain.MemberStudy;
import com.otl.otl.repository.querydsl.StudyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberStudyRepository extends JpaRepository<MemberStudy, Long>, StudyRepositoryCustom {
    List<MemberStudy> findByMemberEmail(String email);
    List<MemberStudy> findByMember(Member member);

}
