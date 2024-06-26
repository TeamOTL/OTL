package com.otl.otl.repository;

import com.otl.otl.domain.Task;
import com.otl.otl.repository.querydsl.StudyRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, StudyRepositoryCustom {

    @Query("SELECT t FROM Task t WHERE t.study IN (SELECT ms.study FROM MemberStudy ms WHERE ms.member.email = :email AND ms.isAccepted = true)")
    List<Task> findTaskByMemberEmailAndIsAccepted(@Param("email") String Email);
    @Query("SELECT t FROM Task t WHERE t.study IN (SELECT ms.study FROM MemberStudy ms WHERE ms.member.email = :email AND ms.isManaged = true)")
    List<Task> findTaskByMemberEmailAndIsManaed(@Param("email") String Email);


}