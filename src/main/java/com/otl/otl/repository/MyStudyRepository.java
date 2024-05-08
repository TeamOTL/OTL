package com.otl.otl.repository;

import com.otl.otl.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyStudyRepository extends JpaRepository<Study, Long> {
}
