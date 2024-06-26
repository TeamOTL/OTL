package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"member", "study"})
public class MemberStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long msNo;          //스터디 참가 고유번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Member member;              //스터디 참가 멤버 이메일

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sno")
    private Study study;                //해당 스터디

    @Column
    @Builder.Default
    private boolean isAccepted = true;     //참가 상태 (참가중, 대기중)

    @Column
    @Builder.Default
    private boolean isManaged = false;    //방장 여부 (방장, 일반)

    @Column
    private String comment;         // 방장에게 한마디 (nullable = true)

    @Transient
    private Long people;           //현재 참가 인원 count 위한 필드

public MemberStudy(Long msNo, Study study, Boolean isAccepted, Boolean isManaged, String comment) {
    this.msNo = msNo;
    this.study = study;
    this.isAccepted = isAccepted;
    this.isManaged = isManaged;
    this.comment = comment;
}

}
