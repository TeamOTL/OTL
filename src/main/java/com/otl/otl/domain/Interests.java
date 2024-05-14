package com.otl.otl.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"study", "member"})
public class Interests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;               // 관심 분야 고유번호

    @Column
    private String interestName;    // 관심 분야 이름

    //interests.sno NULL허용
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "sno")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Member member;





}
