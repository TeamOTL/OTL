package com.otl.otl.service;

import com.otl.otl.domain.Category;
import com.otl.otl.domain.Member;
import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.StudyDTO;
import com.otl.otl.dto.customDTO.StudyCreateCustomDTO;
import com.otl.otl.repository.CategoryRepository;
import com.otl.otl.repository.MemberRepository;
import com.otl.otl.repository.MemberStudyRepository;
import com.otl.otl.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface StudyService {


    void createStudy(StudyCreateCustomDTO studyCreateCustomDTO, String email);


}

