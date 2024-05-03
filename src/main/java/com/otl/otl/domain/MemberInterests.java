package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberInterests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Member member;          // 회원별 관심분야


    @ManyToOne
    @JoinColumn(name = "ino", referencedColumnName = "ino")
    private Interests interests;    // 관심분야 고유번호
}
