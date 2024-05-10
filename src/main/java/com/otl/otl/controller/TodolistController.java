package com.otl.otl.controller;

import com.otl.otl.dto.TodolistDTO;
import com.otl.otl.service.TodolistService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todolists")
@Log4j2
public class TodolistController {

    private final TodolistService todolistService;

    @Autowired
    public TodolistController(TodolistService todolistService) {
        this.todolistService = todolistService;
    }


    @Operation(summary = "User TodoList All Read" , description = "유저의 전체 할 일 리스트 가져오기")
    @GetMapping
    public List<TodolistDTO> getAllTodolists(@AuthenticationPrincipal OAuth2User oauthUser){
        Map<String, Object> kakaoAccount = oauthUser.getAttribute("kakao_account");
        String email = (String) kakaoAccount.get("email");
        return todolistService.getAllTodos(email);
    }

    @Operation(summary = "TodoList Insert" , description = "유저가 입력한 할 일 리스트에 삽입 ")
    @PostMapping
    public TodolistDTO addTodolist(@RequestBody TodolistDTO todolistDTO){
        log.info(todolistDTO);
        /*
        2024-05-10T11:12:21.750+09:00  INFO 3064 --- [otl] [nio-7080-exec-1] c.otl.otl.controller.TodolistController  :
        TodolistDTO(toNo=null, todolistContent=null, isCompleted=false, todoStartDate=null, todoEndDate=null, isDeleted=false,
        memberEmail=river-_@kakao.com)
         */
        return todolistService.addTodo(todolistDTO);
    }
    @Operation(summary = "Select Delete TodoList " , description = "유저가 선택한 할 일 리스트 삭제")
    @DeleteMapping("/{toNo}")
    public void deleteTodolist(@PathVariable("toNo") Long toNo){
        log.info("delete mapping tno->: "+toNo);
        todolistService.deleteTodolist(toNo);
    }



}
