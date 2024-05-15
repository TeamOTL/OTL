package com.otl.otl.service;

import com.otl.otl.domain.Category;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.StudyDTO;
import com.otl.otl.dto.StudyListDTO;
import com.otl.otl.dto.customDTO.StudyCreateCustomDTO;

import java.util.List;

public interface StudyService {

    // <----------------------------------- sue ----------------------------------------->
    void createStudy(StudyCreateCustomDTO studyCreateCustomDTO, String email);


    StudyDTO getStudy(Long sno);


    // <--------------------------------- salmon ---------------------------------------->
    // 추가된 메서드
    List<Study> findUserStudies(String email);
    Study getStudyById(Long sno);



    // <---------------------------------- 99duuk ------------------------------------------>

    /*  WHERE category.cno = ?; (카테고리 드룹다운 선택 submit )
        - 모집 중인 스터디  조회 (모집 종료일이 지나지 않은)
     */
    List<StudyListDTO> findOpenStudies();


    // + 페이지 로드시 category findAll()
    List<Category> getAllCategories();


    /*   ( 2024-05-20 -> d-13 )
       - [카테고리 검색] 모집 중인 스터디 다건 조회
    */
    List<StudyListDTO> findOpenStudiesByCno(Long cno);


    /*  LIKE "%?%"
        - [키워드 검색] 모집 중인 스터디 다건 조회
     */
    List<StudyListDTO> findOpenStudiesByKeyword(String keyword);


      /*   INSERT INTO member_study VALUES      (email = ?, sno = ?, is_managed = 0, is_accpeted = 0;)
        - 모집 페이지 [참가 신청] post 요청
     */

    /*   INSERT INTO study VALUES   (,, ,, , , , ,)   (study 필드 + cno = ? + [interests] + [task] )
        - 스터디 생성
     */
}
