package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long msNo;          //스터디 참가 고유번호

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Member member;      //스터디 참가 멤버 이메일

    @ManyToOne
    @JoinColumn(name = "sno")
    private Study study;        //해당 스터디

    @Column
    @Builder.Default
    private boolean isAccepted = false;     //참가 상태 (참가중, 대기중)

    @Column
    @Builder.Default
    private boolean isManaged = false;    //방장 여부 (방장, 일반)

    @Column
    private String comment;         // 방장에게 한마디 (nullable = true)



}
