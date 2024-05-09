package com.otl.otl.service;

import com.otl.otl.dto.StudyDTO;
import org.springframework.stereotype.Service;

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

}
