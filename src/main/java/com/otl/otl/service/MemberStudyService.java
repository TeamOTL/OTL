package com.otl.otl.service;

import com.otl.otl.domain.MemberStudy;
import com.otl.otl.dto.customDTO.StudyCreateCustomDTO;

import java.util.List;

public interface MemberStudyService {

    //List<MemberStudy> findByMemberEmail(String email);

    MemberStudy requestStudy(Long sno, String email, String comment);
}
