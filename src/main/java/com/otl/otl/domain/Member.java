package com.otl.otl.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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
    private String nickname;           // 회원 이름 (닉네임)

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] memberProfileImage;     // 회원 프로필 사진

    @Column
    private String memberDescription; // 회원 자기 소개

    @OneToMany(mappedBy = "member",
            cascade = {CascadeType.ALL}
            , fetch = FetchType.LAZY)
    @Builder.Default
    private List<Interests> interests = new ArrayList<>();


}