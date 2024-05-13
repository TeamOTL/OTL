package com.otl.otl.service;

import com.otl.otl.domain.Category;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.StudyDTO;
import com.otl.otl.dto.StudyListDTO;

import java.util.List;
import java.util.Optional;

public interface StudyService {
    //등록
    Long register(StudyDTO studyDTO);

    //조회
    StudyDTO readStudy(Long sno);

    //관리자 패이지 -> 스터디방 정보 수정
    void modify(StudyDTO studyDTO);

    //스터디방 삭제
    void remove(Long sno);

    //    검색
//    PageResponseDTO<StudyDTO> list(PageRequestDTO pageRequestDTO);


    Long addStudy(StudyDTO studyDTO);


    // author : 99duuk

    /*
        SELECT * FROM category
     */

    List<Category> getAllCategories();



    /*
        SELECT * FROM study WHERE cno = ?
        카테고리 드롭다운 선택 -> 해당 카테고리 스터디 다건 조회
     */
    List<Study> getStudiesByCno(Long cno);
//    Optional<Study> getStudiesByIno(Long ino);

    List<Study> getStudies();

    /*
    <모집 페이지 "/studyJoin">
     */
    List<StudyListDTO> getAllStudyJoin();

    List<Study> getAllStudyJoin2();


    Study getStudyById(Long sno);

    // 추가된 메서드
    List<Study> findUserStudies(String email);
}
