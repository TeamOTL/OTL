package com.otl.otl.service;

import com.otl.otl.domain.Member;
import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.repository.MemberRepository;
import com.otl.otl.repository.MemberStudyRepository;
import com.otl.otl.repository.StudyRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Log4j2
public class MemberStudyServiceTests {
    @Autowired
    private StudyRepository studyRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberStudyRepository memberStudyRepository;
    @Autowired
    private MemberStudyService memberStudyService;

    @Test
    public void testRequestStudy() {

        // 현재 로그인한 회원의 이메일 주소 (예: y0@gmail.com)
        String loggedInEmail = "youjio2000@gmail.com";
        Long requestSno = 4L;
        String requestComment = "방가방가";
        //String requestComment = null;

        // 스터디 및 멤버 객체 생성
        Study study = new Study();
        study.setSno(requestSno);

        Member member = new Member();
        member.setEmail(loggedInEmail);

        // requestComment가 null이면 멤버의 설명을 사용하도록 설정
        if (requestComment == null) {
            member = memberRepository.findByEmail(loggedInEmail)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid member email"));
            requestComment = member.getMemberDescription();
        }

        MemberStudy memberStudy = memberStudyService.requestStudy(requestSno, loggedInEmail, requestComment);

        memberStudyRepository.saveAndFlush(memberStudy);
    }
}