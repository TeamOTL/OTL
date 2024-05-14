package com.otl.otl.repository;

import com.otl.otl.domain.Member;
import com.otl.otl.domain.Todolist;
import com.otl.otl.dto.TodolistDTO;
import com.otl.otl.service.TodolistService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * 더미테스트용 데이터
 */
@Log4j2
@SpringBootTest
@Transactional
public class TodolistRepositoryTests {

    private final MemberRepository memberRepository;

    @Autowired
    private TodolistService todolistService;

    @MockBean
    private TodolistRepository todolistRepository;
    @Captor
    ArgumentCaptor<Todolist> todolistCaptor;



    @Autowired
    public TodolistRepositoryTests(MemberRepository memberRepository, TodolistRepository todolistRepository) {
        this.memberRepository = memberRepository;
        this.todolistRepository = todolistRepository;
    }



    @Test
    @Commit
    public void testInsert() {
        Member member = new Member(6L,"test@c.com","테스트",null,null,null);

        IntStream.rangeClosed(1, 10).forEach(i -> {
            Todolist todolist = Todolist.builder()
                    .todolistContent("Finish project report")
                    .isCompleted(false)
                    .todoStartDate("2024-05-0"+i)
                    .todoEndDate("2024-05-1"+i)
                    .isDeleted(false)
                    .member(member)
                    .build();

            Todolist result = todolistRepository.save(todolist);

            log.info("Created Todolist: {}", result);
        });

        List<Todolist> todolists = todolistRepository.findAll();
    }

    @Test
    public void testMarkTodolistAsDeleted() {
        Long toNo = 6L;
        Member member = new Member(6L,"test@c.com","테스트",null,null,null);
        Todolist todolist = new Todolist(toNo, "Content", false, "2021-01-01", "2021-01-02", false, member);
        when(todolistRepository.findById(toNo)).thenReturn(Optional.of(todolist));
        when(todolistRepository.save(any(Todolist.class))).thenAnswer(invocation -> invocation.getArgument(0));

        todolistService.updateIsDeletedToTrueByToNo(toNo);

        verify(todolistRepository).save(todolistCaptor.capture());
        Todolist capturedTodolist = todolistCaptor.getValue();
        assertTrue(capturedTodolist.isDeleted(), "Todolist should be marked as deleted but it wasn't.");
        log.info("capturedTodolist : " + capturedTodolist);
        log.info("capturedTodolist.isDeleted() : " + capturedTodolist.isDeleted());
    }

    @Test
    public void testMarkTodolistAsCompleted() {
        Long toNo = 1L;
        Member member = new Member(6L,"test@c.com","테스트",null,null,null);
        Todolist todolist = new Todolist(toNo, "Content", false, "2021-01-01", "2021-01-02", false, member);
        when(todolistRepository.findById(toNo)).thenReturn(Optional.of(todolist));
        when(todolistRepository.save(any(Todolist.class))).thenReturn(todolist);

        todolistService.updateIsCompletedToTrueByToNo(toNo);

        verify(todolistRepository).save(todolistCaptor.capture());
        Todolist capturedTodolist = todolistCaptor.getValue();
        assertTrue(capturedTodolist.isCompleted(), "Todolist should be marked as completed but it wasn't.");
        log.info("capturedTodolist : " + capturedTodolist);
        log.info("capturedTodolist.isCompleted() : " + capturedTodolist.isCompleted());
    }
}
