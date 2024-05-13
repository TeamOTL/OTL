package com.otl.otl.repository;

import com.otl.otl.domain.Study;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest
@Transactional
public class StudyRepositoryTests {

    @Autowired
    private StudyRepository StudyRepository;

    @Test
    public void findAll() {
        List<Study> studyList = StudyRepository.findAll();


        log.info(studyList);
    }


    @Test
    public void findAll2() {
        List<Study> studyList = StudyRepository.findAll();

        for (Study study : studyList) {
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(studyList);
        }
    }



    //모집 종료일이 지나지 않은 스터디 조회("/studyjoin") get
    @Test
    public void findFilterREndDate(){
        List<Study> studyList = StudyRepository.findAllByCurDate();

        for (Study study : studyList) {
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(dDay);
        }

        log.info(studyList);
    }


}
