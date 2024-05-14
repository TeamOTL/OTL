package com.otl.otl.repository;

import com.otl.otl.domain.Category;
import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest
@Transactional
public class StudyRepositoryTests {

    @Autowired
    private StudyRepository StudyRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private StudyRepository studyRepository;


    //모집 종료일이 지나지 않은 스터디 조회("/studyjoin") get
    // d-14, 2024-05-20
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
    /*
    [Study(sno=1, studyName=ddddd, studyDescription=dd, studyPlan=ddd, maxMember=5, firstDate=2024-05-31, rStartDate=2024-05-01, rEndDate=2024-05-20,
        category=Category(cno=1, categoryName=백엔드, categoryImage=222), dDay=D-6,
        tasks=[Task(tno=1, taskTitle=1, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-05-13, isCompleted=false),
                Task(tno=3, taskTitle=ㅁ, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-05-15, isCompleted=false),
                Task(tno=5, taskTitle=ㅊㅍㅁ, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-06-13, isCompleted=false),
                Task(tno=7, taskTitle=ㅠㅁ츄, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-06-15, isCompleted=false)],
        interests=[Interests(ino=1, interestName=자바, 리눅스 ), Interests(ino=3, interestName=ㅗㅗㅗㅗ)]),

    Study(sno=2, studyName=ㅁㄴㅇㄹ, studyDescription=2ㅇㄹㅁㅇㄴㄹㅁㄴㅇㄹ, studyPlan=ㅇㅇㅇ, maxMember=2, firstDate=2024-05-24, rStartDate=2024-05-05, rEndDate=2024-05-23,
        category=Category(cno=2, categoryName=프론트엔드, categoryImage=22), dDay=D-9,
        tasks=[Task(tno=2, taskTitle=ㄴㅁㅇㄹ, taskDate=ㅁㄹㅇㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-05-30, isCompleted=true),
            Task(tno=4, taskTitle=ㅁㄴㅇㄹ, taskDate=ㅁㄹㅇㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-05-31, isCompleted=true),
            Task(tno=6, taskTitle=ㅠㅁ, taskDate=ㅁㄹㅇㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-06-30, isCompleted=true),
            Task(tno=8, taskTitle=ㅠㅁㅊ, taskDate=ㅁㄹㅇㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-06-31, isCompleted=true)],
        interests=[Interests(ino=2, interestName=리눅스, 도커)]),

    Study(sno=3, studyName=ddddd, studyDescription=dd, studyPlan=ddd, maxMember=5, firstDate=2024-05-22, rStartDate=2024-04-01, rEndDate=2024-05-20,
        category=Category(cno=1, categoryName=백엔드, categoryImage=222), dDay=D-6,
        tasks=[], interests=[]),

    Study(sno=5, studyName=ㅁㅇㄴㅎ, studyDescription=ㅁㄴㅇㅎㅁㄴㅇㅎㅁ, studyPlan=ㅇㅇㅇ, maxMember=2, firstDate=2024-05-16, rStartDate=2024-04-05, rEndDate=2024-05-30,
        category=Category(cno=2, categoryName=프론트엔드, categoryImage=22), dDay=D-16, tasks=[], interests=[])]
    */

}



