package com.otl.otl.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Interests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;               // 관심 분야 고유번호

    @Column
    private String interestName;    // 관심 분야 이름


}
