package com.otl.otl.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;               // 회원 고유번호

    @Column(unique = true)
    private String email;         // 회원 이메일

    @Column(nullable = false)
    private String name;           // 회원 이름 (닉네임)

    @Column
    private String userProfile;     // 회원 프로필 사진

    @Column
    private String userDescription; // 회원 자기 소개


//    @OneToMany(mappedBy = "email")
//    private List<Board> Boards;

}