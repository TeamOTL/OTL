package com.otl.otl.service;

import com.otl.otl.dto.TodolistDTO;

import java.util.List;
import java.util.Optional;

public interface TodolistService {
    List<TodolistDTO> getAllTodos(String email);

    TodolistDTO addTodo(TodolistDTO todolistDTO);

    void deleteTodolist(Long toNo);

    Optional<TodolistDTO> findById(Long toNo);
}
