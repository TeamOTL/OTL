package com.otl.otl.service;

import com.otl.otl.domain.Todolist;
import com.otl.otl.dto.TodolistDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

public interface TodolistService {
    List<TodolistDTO> getAllTodos(String email);
    TodolistDTO addTodo(TodolistDTO todolistDTO);
    void deleteTodolist(Long toNo);
}
