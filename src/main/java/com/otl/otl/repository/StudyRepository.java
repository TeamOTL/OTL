package com.otl.otl.repository;

import com.otl.otl.domain.Study;
import com.otl.otl.repository.querydsl.StudyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudyRepository extends JpaRepository<Study, Long>, StudyRepositoryCustom {

    // cno 별 Study 조회
    List<Study> findByCategoryCno(Long cno);

//    Optional<Study> findByInterestsIno(Long ino);

    // 모든 Study 조회
    List<Study> findAll();

}
