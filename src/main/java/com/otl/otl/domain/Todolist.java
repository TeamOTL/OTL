package com.otl.otl.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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


    @Column(nullable = false)
    @Builder.Default
    private boolean isCompleted = false;      // 투두리스트 완료 여부


    @Column(nullable = false)
    private String todoStartDate;   // 투두 시작 날짜

    @Column(nullable = false)
    private String todoEndDate; // 투두 종료 날짜


    @Column(nullable = false)
    @Builder.Default
    private boolean isDeleted = false;      // 투두리스트 삭제 여부

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    @JsonBackReference
    private Member member;          // 투두리스트 작성자

}
