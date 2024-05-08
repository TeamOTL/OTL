package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudyInterests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long siNo;                          // 스터디, 관심 분야 다대다 관계 해소 중간 테이블

    @ManyToOne
    @JoinColumn(name = "ino")
    private Interests interests;

    @ManyToOne
    @JoinColumn(name = "sno")
    private Study study;
}
