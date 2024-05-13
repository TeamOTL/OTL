package com.otl.otl.repository;


import com.otl.otl.domain.MemberStudy;
import com.otl.otl.repository.querydsl.StudyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//    List<Long> findSnoByEmailAndAccepted(String email);

@Repository
public interface MemberStudyRepository extends JpaRepository<MemberStudy, Long>, StudyRepositoryCustom {
//    List<Long> findSnoByMemberEmailAndIsAccepted(String email, boolean isAccepted);

    // email과 isAccepted 조건에 해당하는 MemberStudy의 개수를 조회하는 메서드
//    long countByEmailAndIsAccepted(String email, boolean isAccepted);

}







