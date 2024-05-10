package com.otl.otl.repository;


import com.otl.otl.domain.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TodolistRepository extends JpaRepository<Todolist, Long> {

    List<Todolist> findByMemberEmail(String email);
}
