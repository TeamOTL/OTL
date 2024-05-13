package com.otl.otl.service;

import com.otl.otl.domain.Member;
import com.otl.otl.domain.Todolist;
import com.otl.otl.dto.TodolistDTO;
import com.otl.otl.repository.MemberRepository;
import com.otl.otl.repository.TodolistRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TodolistServiceImpl implements TodolistService {

    private final TodolistRepository todolistRepository;

    private final MemberRepository memberRepository;

    public TodolistServiceImpl(TodolistRepository todolistRepository, MemberRepository memberRepository) {
        this.todolistRepository = todolistRepository;
        this.memberRepository = memberRepository;
    }


    // DTO -> Domain 변환
    private Todolist toDomain(TodolistDTO todolistDTO, Member member) {
        return Todolist.builder()
                .toNo(todolistDTO.getToNo())
                .todolistContent(todolistDTO.getTodolistContent())
                .isCompleted(todolistDTO.isCompleted())
                .todoStartDate(todolistDTO.getTodoStartDate())
                .todoEndDate(todolistDTO.getTodoEndDate())
                .isDeleted(todolistDTO.isDeleted())
                .member(member)
                .build();
    }

    // Domain -> DTO 변환
    public TodolistDTO toDTO(Todolist todolist) {
        return TodolistDTO.builder()
                .toNo(todolist.getToNo())
                .todolistContent(todolist.getTodolistContent())
                .isCompleted(todolist.isCompleted())
                .todoStartDate(todolist.getTodoStartDate())
                .todoEndDate(todolist.getTodoEndDate())
                .isDeleted(todolist.isDeleted())
                .memberEmail(todolist.getMember().getEmail())
                .build();
    }

    @Override
    public List<TodolistDTO> getAllTodos(String email) {
        return todolistRepository.findByMemberEmail(email).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TodolistDTO addTodo(TodolistDTO todolistDTO) {
        Member member = memberRepository.findByEmail(todolistDTO.getMemberEmail()).orElseThrow(() -> new RuntimeException("Member not found with email: " + todolistDTO.getMemberEmail()));

        Todolist todolist = toDomain(todolistDTO, member);

        Todolist savedTodolist = todolistRepository.save(todolist);
        return toDTO(savedTodolist);
    }

    @Override
    public void deleteTodolist(Long toNo) {
        if (!todolistRepository.existsById(toNo)) {
            throw new RuntimeException("Todolist not found with id: " + toNo);
        }
        todolistRepository.deleteById(toNo);
    }

    @Override
    public Optional<TodolistDTO> findById(Long toNo) {
        return todolistRepository.findById(toNo)
                .map(this::toDTO);
    }


}
