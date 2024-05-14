package com.otl.otl.service;

import com.otl.otl.domain.Category;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.StudyDTO;
import com.otl.otl.dto.StudyListDTO;

import java.util.List;

public interface StudyService {
    //등록
    Long register(StudyDTO studyDTO);
    //조회
    //관리자 패이지 -> 스터디방 정보 수정
    void modify(StudyDTO studyDTO);
    //스터디방 삭제
    void remove(Long sno);
    //    검색
//    PageResponseDTO<StudyDTO> list(PageRequestDTO pageRequestDTO);


    Study getStudyById(Long sno);
    // 추가된 메서드
    List<Study> findUserStudies(String email);




// <---------------------------------------------------------------------------->

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
}
