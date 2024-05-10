package com.otl.otl.repository;

import com.otl.otl.domain.Member;
import com.otl.otl.domain.Todolist;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * 더미테스트용 데이터
 */
@Log4j2
@SpringBootTest
@Transactional
public class TodolistRepositoryTests {

    private final MemberRepository memberRepository;


    private final TodolistRepository todolistRepository;

    private Member member;

    @Autowired
    public TodolistRepositoryTests(MemberRepository memberRepository, TodolistRepository todolistRepository) {
        this.memberRepository = memberRepository;
        this.todolistRepository = todolistRepository;
    }

    @BeforeEach
    void setup() {
        // 먼저 테스트용 멤버를 생성
        member = Member.builder()
                .email("test1@c.com")
                .nickname("Test User")
                .build();
        member = memberRepository.save(member);
    }

    @Test
    @Commit
    public void testInsert() {
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
//
//    @Test
//    @Commit
//    public void testFindById() {
//        Todolist todolist = Todolist.builder()
//                .todolistContent("Find me")
//                .startDate("2024-05-07")
//                .endDate("2024-05-09")
//                .completed(false)
//                .isDeleted(false)
//                .member(testMember)
//                .build();
//
//        Todolist savedTodolist = todolistRepository.save(todolist);
//
//        Optional<Todolist> foundTodolist = todolistRepository.findById(savedTodolist.getToNo());
//
//        assertThat(foundTodolist).isPresent();
//        assertThat(foundTodolist.get().getTodolistContent()).isEqualTo("Find me");
//    }
//
//    @Test
//    @Commit
//    public void testUpdate() {
//        Todolist todolist = Todolist.builder()
//                .todolistContent("Original content")
//                .startDate("2024-05-07")
//                .endDate("2024-05-09")
//                .completed(false)
//                .isDeleted(false)
//                .member(testMember)
//                .build();
//
//        Todolist savedTodolist = todolistRepository.save(todolist);
//
//        savedTodolist.setTodolistContent("Updated content");
//        Todolist updatedTodolist = todolistRepository.save(savedTodolist);
//
//        assertThat(updatedTodolist.getTodolistContent()).isEqualTo("Updated content");
//    }
//
//    @Test
//    @Commit
//    public void testDelete() {
//        Todolist todolist = Todolist.builder()
//                .todolistContent("To be deleted")
//                .startDate("2024-05-07")
//                .endDate("2024-05-09")
//                .completed(false)
//                .isDeleted(false)
//                .member(testMember)
//                .build();
//
//        Todolist savedTodolist = todolistRepository.save(todolist);
//
//        todolistRepository.delete(savedTodolist);
//
//        Optional<Todolist> deletedTodolist = todolistRepository.findById(savedTodolist.getToNo());
//
//        assertThat(deletedTodolist).isEmpty();
//    }
//
//    @Test
//    @Commit
//    public void testFindByMember() {
//        IntStream.rangeClosed(1, 3).forEach(i -> {
//            Todolist todolist = Todolist.builder()
//                    .todolistContent("Member content " + i)
//                    .startDate("2024-05-07")
//                    .endDate("2024-05-09")
//                    .completed(false)
//                    .isDeleted(false)
//                    .member(testMember)
//                    .build();
//
//            todolistRepository.save(todolist);
//        });
//
//        List<Todolist> todolistsByMember = todolistRepository.findByMember(testMember);
//
//        assertThat(todolistsByMember).hasSize(3);
//        todolistsByMember.forEach(todolist ->
//                assertThat(todolist.getMember().getEmail()).isEqualTo("test@example.com")
//        );
//    }
}
