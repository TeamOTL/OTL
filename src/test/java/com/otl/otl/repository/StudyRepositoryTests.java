package com.otl.otl.repository;

import com.otl.otl.domain.Study;
import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void findByCurDate(){

        List<Study> studyList = StudyRepository.findAllByCurDate();

        for (Study study : studyList) {
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(dDay);
        }

        log.info(studyList);
    }

    //모집 종료일이 지나지 않은 스터디 조회 By cno ("/studyjoin") get
    // WHERE curDate ADD cno = ?;
    @Test
    public void findByCurDateByCno(){
        Long cno = 1L;

        List<Study> studyList = StudyRepository.findAllByCurDateByCno(cno);

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


    @Test
    public void findByCategoryKeyword(){
        String keyword = "하이";

        List<Study> studyList = StudyRepository.findAllByCurDateByKeyword(keyword);

        for (Study study : studyList) {
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(dDay);
        }

        log.info(studyList);
    }

//    @Test
//    @Transactional
//    public void findByCategoryCNT(){
//        // 검색할 키워드 설정
//
//
//        // 해당 키워드를 포함한 스터디 조회
//        List<Study> studyList = studyRepository.findAllByCurDate();
//
//        // 해당 스터디의 sno를 사용하여 승인된 참가자 수 조회
//        List<Tuple> acceptedCountBySno = studyRepository.countAcceptedBySno();
//        log.info(acceptedCountBySno);
//
//        // 각 스터디의 D-day 및 People 필드 설정
//        for (Study study : studyList) {
//            // D-day 설정
//            String dDay = study.calCombinedDday();
//            study.setDDay(dDay);
//
//            for (Tuple tuple : acceptedCountBySno) {
//                Long sno = tuple.get(0, Long.class); // 첫 번째 값은 스터디의 고유 번호
//                Long acceptedCount = tuple.get(1, Long.class); // 두 번째 값은 승인된 참가자 수
//                if (study.getSno().equals(sno)) {
//                    study.setPeople(acceptedCount.toString());
//                    break;
//            }
//
//                log.info("Study name: {}", study.getStudyName());
//                log.info("Study sno: {}", study.getSno());
//                log.info("D-day: {}", study.getDDay());
//                log.info("People: {}", study.getPeople());
//        }
//
//
//
//
//        // 결과 출력
//            log.info(studyList);
//        }
//    }
@Test
@Transactional
public void findAllByCurDateCNT(){
    // 검색할 키워드 설정

    // 해당 키워드를 포함한 스터디 조회
    List<Study> studyList = studyRepository.findAllByCurDate();

    // 승인된 참가자 수를 모든 스터디에 대해 한 번만 조회
    Map<Long, Long> acceptedCountMap = new HashMap<>();
    List<Tuple> acceptedCountList = studyRepository.countAcceptedBySno();

    for (Tuple tuple : acceptedCountList) {
        Long sno = tuple.get(0, Long.class); // 첫 번째 값은 스터디의 고유 번호
        Long acceptedCount = tuple.get(1, Long.class); // 두 번째 값은 승인된 참가자 수
        acceptedCountMap.put(sno, acceptedCount);
    }

    // 각 스터디의 D-day 및 People 필드 설정
    for (Study study : studyList) {
        // D-day 설정
        String dDay = study.calCombinedDday();
        study.setDDay(dDay);

        // 해당 스터디의 승인된 참가자 수 설정
        Long acceptedCount = acceptedCountMap.get(study.getSno());
        if (acceptedCount != null) {
            study.setPeople(acceptedCount.toString());
        }

        log.info("Study name: {}", study.getStudyName());
        log.info("Study sno: {}", study.getSno());
        log.info("D-day: {}", study.getDDay());
        log.info("People: {}", study.getPeople());
        log.info(" ");
    }

//    // 결과 출력
//    log.info("Study List: {}", studyList);
}




    @Test
    public void countAcceptedBySno(){
        List<Tuple> results = StudyRepository.countAcceptedBySno();

        log.info(results);
    }




}



