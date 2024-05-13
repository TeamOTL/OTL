package com.otl.otl.dto;

import com.otl.otl.domain.Member;
import com.otl.otl.domain.Study;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberStudyDTO {
    private Long msNo;          //스터디 참가 고유번호
    private Member member;      //스터디 참가 멤버 이메일
    private Study study;        //해당 스터디
    private boolean isAccepted;    //참가 상태 (참가중, 대기중)
    private boolean isManaged;    //방장 여부 (방장, 일반)
    private String comment;         // 방장에게 한마디 (nullable = true)
//    @Transient
//    private int people;

}
