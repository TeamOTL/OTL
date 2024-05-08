package com.otl.otl.repository;

import com.otl.otl.domain.Member;
import com.otl.otl.domain.Todolist;
import groovy.util.logging.Log4j2;
import jakarta.transaction.Transactional;
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

    @Autowired
    private TodolistRepository todolistRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member testMember;

    @BeforeEach
    public void setupMember() {
        // not null있으면 .빌더에 추가
        Optional<Member> existingMember = memberRepository.findByEmail("test@example.com");
        if (existingMember.isPresent()) {
            testMember = existingMember.get();
        } else {
            testMember = Member.builder()
                    .email("test@example.com")
                    .nickname("TestUser")
                    .build();
            testMember = memberRepository.save(testMember);
        }
    }

    @Test
    @Commit
    public void testInsert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Todolist todolist = Todolist.builder()
                    .todolistContent("content.." + i)
                    .startDate("2024-05-07")
                    .endDate("2024-05-09")
                    .completed(false)
                    .isDeleted(false)
                    .member(testMember)
                    .build();

            Todolist result = todolistRepository.save(todolist);

            log.info("Created Todolist: {}", result);
        });

        List<Todolist> todolists = todolistRepository.findAll();
        assertThat(todolists).hasSize(10);
    }

    @Test
    @Commit
    public void testFindById() {
        Todolist todolist = Todolist.builder()
                .todolistContent("Find me")
                .startDate("2024-05-07")
                .endDate("2024-05-09")
                .completed(false)
                .isDeleted(false)
                .member(testMember)
                .build();

        Todolist savedTodolist = todolistRepository.save(todolist);

        Optional<Todolist> foundTodolist = todolistRepository.findById(savedTodolist.getToNo());

        assertThat(foundTodolist).isPresent();
        assertThat(foundTodolist.get().getTodolistContent()).isEqualTo("Find me");
    }

    @Test
    @Commit
    public void testUpdate() {
        Todolist todolist = Todolist.builder()
                .todolistContent("Original content")
                .startDate("2024-05-07")
                .endDate("2024-05-09")
                .completed(false)
                .isDeleted(false)
                .member(testMember)
                .build();

        Todolist savedTodolist = todolistRepository.save(todolist);

        savedTodolist.setTodolistContent("Updated content");
        Todolist updatedTodolist = todolistRepository.save(savedTodolist);

        assertThat(updatedTodolist.getTodolistContent()).isEqualTo("Updated content");
    }

    @Test
    @Commit
    public void testDelete() {
        Todolist todolist = Todolist.builder()
                .todolistContent("To be deleted")
                .startDate("2024-05-07")
                .endDate("2024-05-09")
                .completed(false)
                .isDeleted(false)
                .member(testMember)
                .build();

        Todolist savedTodolist = todolistRepository.save(todolist);

        todolistRepository.delete(savedTodolist);

        Optional<Todolist> deletedTodolist = todolistRepository.findById(savedTodolist.getToNo());

        assertThat(deletedTodolist).isEmpty();
    }

    @Test
    @Commit
    public void testFindByMember() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            Todolist todolist = Todolist.builder()
                    .todolistContent("Member content " + i)
                    .startDate("2024-05-07")
                    .endDate("2024-05-09")
                    .completed(false)
                    .isDeleted(false)
                    .member(testMember)
                    .build();

            todolistRepository.save(todolist);
        });

        List<Todolist> todolistsByMember = todolistRepository.findByMember(testMember);

        assertThat(todolistsByMember).hasSize(3);
        todolistsByMember.forEach(todolist ->
                assertThat(todolist.getMember().getEmail()).isEqualTo("test@example.com")
        );
    }
}
