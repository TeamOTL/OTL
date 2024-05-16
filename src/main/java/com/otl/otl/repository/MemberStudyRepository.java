package com.otl.otl.repository;

import com.otl.otl.domain.Member;
import com.otl.otl.domain.MemberStudy;
import com.otl.otl.repository.querydsl.StudyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberStudyRepository extends JpaRepository<MemberStudy, Long>, StudyRepositoryCustom {
    List<MemberStudy> findByMemberEmail(String email);
    List<MemberStudy> findByMember(Member member);
    Optional<MemberStudy> findByMemberAndStudySno(Member member, Long sno);
    List<MemberStudy> findByStudySnoAndIsAccepted(Long sno, Boolean isAccepted);
    Optional<MemberStudy> findByMemberEmailAndIsAccepted(String email, Boolean isAccepted);
    void deleteByMember(Member member);

}
