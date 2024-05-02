package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Todolist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toNo;               // 투두리스트 고유번호

    @Column(nullable = false)
    private String todolistContent;         // 투두리스트 내용

    @Column
    private boolean completed;      // 투두리스트 완료 여부

    @Column
    private boolean isDeleted;      // 투두리스트 삭제 여부

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Member member;          // 투두리스트 작성자

}
