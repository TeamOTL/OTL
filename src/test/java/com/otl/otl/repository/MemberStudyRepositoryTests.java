package com.otl.otl.repository;

import com.otl.otl.domain.MemberStudy;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest
@Transactional
public class MemberStudyRepositoryTests {

    @Autowired
    private MemberStudyRepository memberStudyRepository;

    @Test
    public void findSnoByEmailAndIsAccepted1() {
        // Given
        String email = "wer2587@naver.com"; // 테스트할 member

        // When
        List<Long> memberStudyList = memberStudyRepository.findSnoByEmailAndIsAccepted(email);

        //Then
        for (Long sno : memberStudyList) {
            System.out.println("sno=" + sno);

        }
    }

    @Test
    public void findMemberStudyByIsAccepted(){
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberStudyByIsAccepted();

        log.info(memberStudyList);
    }


    @Test
    public void findSnoByEmailAndIsAccepted2() {
        // Given
        String email = "wer2587@naver.com"; // 테스트할 member

        // When
        List<Long> memberStudyList = memberStudyRepository.findSnoByEmailAndIsAccepted(email);

        //Then
        for (Long sno : memberStudyList) {
            System.out.println("sno=" + sno);

        }
    }


    //     WHERE email = ?
    //     AND is_accpeted = 1
    @Test
    public void findByEmailAndIsAccepted() {
        // Given
        String email = "wer2587@naver.com"; // 테스트할 member

        // When
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberStudyByEmailAndIsAccepted(email);

        //Then
        log.info(memberStudyList);
    }


    //     WHERE email = ?
    //     AND is_accpeted = 1
    //     AND sno = ?
    @Test
    public void findByEmailAndIsAcceptedAndSno() {
        // Given
        String email = "wer2587@naver.com"; // 테스트할 member
        Long sno = 2L;

        // When
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberStudyByEmailAndIsAcceptedAndSno(email, sno);

        //Then
        log.info(memberStudyList);
    }

}
