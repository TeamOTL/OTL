//package com.otl.otl.domain;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class taskFeedback {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long fno;                   // 피드백 고유번호
//
//    @Column
//    private String feedbackContent;     // 피드백 내용
//
//    @ManyToOne
//    @JoinColumn(name = "email", referencedColumnName = "email")
//    private Member member;              // 피드백 작성자
//
//    @ManyToOne
//    @JoinColumn(name = "tno")
//    private Task task;
//
//}
