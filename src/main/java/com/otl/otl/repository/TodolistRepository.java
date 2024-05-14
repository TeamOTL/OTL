package com.otl.otl.repository;


import com.otl.otl.domain.Todolist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
//updateIsCompletedToTrueByToNo
public interface TodolistRepository extends JpaRepository<Todolist, Long> {

    List<Todolist> findByMemberEmail(String email);

    List<Todolist> findByMemberEmailAndIsCompletedFalseAndIsDeletedFalse(String email);


    // 이메일을 기준으로 완료된 Todolist 항목들을 조회합니다.
    List<Todolist> findByMember_EmailAndIsCompletedTrue(String email);

    // 이메일을 기준으로 삭제된 Todolist 항목들을 조회합니다.
    List<Todolist> findByMember_EmailAndIsDeletedTrue(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Todolist t SET t.isCompleted = true WHERE t.toNo = :toNo")
    void updateIsCompletedToTrueByToNo(@Param("toNo") Long toNo);

    @Modifying
    @Transactional
    @Query("UPDATE Todolist t SET t.isDeleted = true WHERE t.toNo = :toNo")
    void updateIsDeletedToTrueByToNo(@Param("toNo") Long toNo);
}
