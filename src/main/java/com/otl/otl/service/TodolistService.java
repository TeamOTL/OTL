package com.otl.otl.service;

import com.otl.otl.domain.Todolist;
import com.otl.otl.dto.TodolistDTO;

import java.util.List;
import java.util.Optional;

public interface TodolistService {
    List<TodolistDTO> findByMemberEmail(String email);
    List<TodolistDTO> getTodosListIsCompleteFalseANDIsDeletedFalse(String email);

    TodolistDTO addTodo(TodolistDTO todolistDTO);

    void deleteTodolist(Long toNo);

    Optional<TodolistDTO> findById(Long toNo);

    void updateIsDeletedToTrueByToNo(Long toNo);
    void updateIsCompletedToTrueByToNo(Long toNo);

    List<Todolist> getCompletedTodolists(String email);
    List<Todolist> getDeletedTodolists(String email);

}
