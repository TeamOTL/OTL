package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;                       //스터디 고유번호

    @Column(nullable = false)
    private String studyName;               //스터디 이름

    @Column(nullable = false)
    private String studyDescription;        //스터디 소개

    @Column(nullable = false)
    private String studyPlan;               //스터디 일정 (매주 월, 목)

    @Column(nullable = false)
    private Long maxMember;                //최대 참가 인원

    @Column(nullable = false)
    private String firstDate;                //스터디 시작일

    @Column(nullable = false)
    private String rStartDate;                  // 모집 기간_시작일

    @Column(nullable = false)
    private String rEndDate;                  // 모집 기간_종료일

    @ManyToOne
    @JoinColumn(name = "cno")
    private Category category;
    //토) category 1:1 -> n:1 (1:1시 cno가 study에 유니크키 되어 중복 불가)

    @Transient
    private String dDay;


    @OneToMany(mappedBy = "study",
            cascade = {CascadeType.ALL}
            , fetch = FetchType.LAZY)
    @Builder.Default
    private List<Task> tasks = new ArrayList<>();


    @OneToMany(mappedBy = "study",
            cascade = {CascadeType.ALL}
            , fetch = FetchType.LAZY)
    @Builder.Default
    private List<Interests> interests = new ArrayList<>();



    @OneToMany(mappedBy = "study", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberStudy> memberStudies = new ArrayList<>();


//    //스터디방 생성시 주차 추가
//    public void addTask(Integer taskWeek, String taskTitle, Study study) {
//
//        Task task = Task.builder()
//                .taskWeek(taskWeek)
//                .taskTitle(taskTitle)
//                .study(study) // Study 엔터티
//                .build();
//        tasks.add(task);
//
//    }
//
//    //스터디방 생성시 주차 추가
//    public void interests(String interersts_name, Study study) {
//
//        Task task = Task.builder()
//                .interests_name(/)
//                .Study(study) // Study 엔터티
//                .build();
//        tasks.add(task);
//
//    }



    // 날짜 계산 메서드
    public String calCombinedDday() {
        LocalDate today = LocalDate.now();
        LocalDate rEndDateLocalDate = LocalDate.parse(rEndDate); // 모집 종료일을 LocalDate로 변환
        LocalDate firstDateLocalDate = LocalDate.parse(firstDate); // 스터디 시작일을 LocalDate로 변환

        if (today.isAfter(rEndDateLocalDate)) {
            // 현재 날짜가 모집 종료일 이후인 경우
            long daysDifference = ChronoUnit.DAYS.between(today, firstDateLocalDate);
            return "D+" + Math.abs(daysDifference);
        } else {
            // 현재 날짜가 모집 종료일 이전인 경우
            long daysDifference = ChronoUnit.DAYS.between(today, rEndDateLocalDate);
            return "D-" + daysDifference;
        }
    }


    public void setDDay(String dDay) {
        this.dDay = dDay;
    }


    public List<Member> getMembers() {
        List<Member> members = new ArrayList<>();
        for (MemberStudy memberStudy : memberStudies) {
            members.add(memberStudy.getMember());
        }
        return members;
    }

    public void addTask(String taskDate, String taskTitle) {

        Task task = Task.builder()
                .taskDate(taskDate)
                .taskTitle(taskTitle)
                .study(this) // Study 엔터티
                .build();
        tasks.add(task);

    }

    //스터디방 생성시 흥미 추가
    public void addInterest(String interestName) {

        Interests interest = Interests.builder()
                .interestName(interestName)
                .study(this)
                .build();
        interests.add(interest);

    }


}

