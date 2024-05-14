package com.otl.otl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudyListDTO {

    private StudyDTO studyDTO;
    private TaskDTO taskDTO;

    private InterestsDTO interestsDTO;

    private CategoryDTO categoryDTO;
    private MemberStudyDTO memberStudyDTO;


    private Long mno;               // 회원 고유번호
    private String email;         // 회원 이메일
    private String nickname;           // 회원 이름 (닉네임)
    private String memberProfileImage;     // 회원 프로필 사진
    private String memberDescription; // 회원 자기 소개


    private Long msNo;          //스터디 참가 고유번호
    private boolean isAccepted;    //참가 상태 (참가중, 대기중)
    private boolean isManaged;    //방장 여부 (방장, 일반)
    private String comment;         // 방장에게 한마디 (nullable = true)
//    @Transient
//    private int people;

    private Long sno;                       //스터디 고유번호
    private String studyName;               //스터디 이름
    private String studyDescription;        //스터디 소개
    private String studyPlan;               //스터디 일정 (매주 월, 목)
    private Long maxMember;                //최대 참가 인원
    private String firstDate;                //스터디 시작일
    private String rStartDate;                  // 모집 기간_시작일
    private String rEndDate;                  // 모집 기간_종료일
    private String dDay;                       //dday

    private List<String> memberNicknames;     // 멤버 닉네임 리스트 추가

    private List<TaskDTO> taskDTOList;
    private List<InterestsDTO> interestsDTOList;


    private Long cno;               // 카테고리 고유 번호 pk
    private String categoryName;    // 카테고리 이름


    private Long categoryCno;


    private String categoryImage;


    private List<TaskDTO> tasks;
    private List<InterestsDTO> interests;

}
