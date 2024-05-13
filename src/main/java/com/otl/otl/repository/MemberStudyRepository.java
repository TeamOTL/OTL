package com.otl.otl.repository;

import com.otl.otl.domain.MemberStudy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberStudyRepository extends JpaRepository<MemberStudy, Long>{
    List<MemberStudy> findByMemberEmail(String email);

}
