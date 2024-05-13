package com.otl.otl.controller;

import com.otl.otl.dto.TodolistDTO;
import com.otl.otl.service.TodolistService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/todolists")
@Log4j2
public class TodolistController {

    private final TodolistService todolistService;

    @Autowired
    public TodolistController(TodolistService todolistService) {
        this.todolistService = todolistService;
    }


    @Operation(summary = "User TodoList All Read", description = "유저의 전체 할 일 리스트 가져오기")
    @GetMapping
    public ResponseEntity<List<TodolistDTO>> getAllTodolists(@AuthenticationPrincipal OAuth2User oauthUser) {
        try {
            Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
            String email = (String) kakaoAccount.get("email");
            List<TodolistDTO> todos = todolistService.getAllTodos(email);
            return ResponseEntity.ok().body(todos);
        } catch (Exception e) {
            log.error("Error retrieving todolists for user: {}" + oauthUser.getName(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to retrieve todolists for user: " + oauthUser.getName());
        }
    }

    @Operation(summary = "TodoList Insert", description = "유저가 입력한 할 일 리스트에 삽입 ")
    @PostMapping
    public ResponseEntity<TodolistDTO> addTodolist(@RequestBody TodolistDTO todolistDTO) {
        try {
//            log.info(todolistDTO);
        /*
        2024-05-10T11:12:21.750+09:00  INFO 3064 --- [otl] [nio-7080-exec-1] c.otl.otl.controller.TodolistController  :
        TodolistDTO(toNo=null, todolistContent=null, isCompleted=false, todoStartDate=null, todoEndDate=null, isDeleted=false,
        memberEmail=river-_@kakao.com)
         */
            TodolistDTO savedTodo = todolistService.addTodo(todolistDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
        } catch (Exception e) {
            log.error("Error adding todolist: {}", todolistDTO, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Select Delete TodoList ", description = "유저가 선택한 할 일 리스트 삭제")
    @DeleteMapping("/{toNo}")
    public ResponseEntity<?> deleteTodolist(@PathVariable("toNo") Long toNo, @AuthenticationPrincipal OAuth2User oauthUser) {
        try {
            Optional<TodolistDTO> todo = todolistService.findById(toNo);
            if (todo.isPresent() && todo.get().getMemberEmail().equals(oauthUser.getAttribute("kakao_account"))) {
                todolistService.deleteTodolist(toNo);
                return ResponseEntity.ok().build();

            }else{
                log.error("Unauthorized access attempt to delete todolist: {}", toNo);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } catch (Exception e) {
            log.error("Error deleting todolist: {}", toNo, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
