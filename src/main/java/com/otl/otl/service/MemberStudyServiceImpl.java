package com.otl.otl.service;

import com.otl.otl.domain.*;
import com.otl.otl.dto.MemberStudyDTO;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjectionImpl;
import com.otl.otl.dto.StudyListDTO;
import com.otl.otl.repository.MemberStudyRepository;
import com.otl.otl.service.converter.CustomConverters;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberStudyServiceImpl implements MemberStudyService {

    private final MemberStudyRepository memberStudyRepository;
    private final CustomConverters customConverters;

    public MemberStudyServiceImpl(MemberStudyRepository memberStudyRepository, CustomConverters customConverters) {
        this.memberStudyRepository = memberStudyRepository;
        this.customConverters = customConverters;
    }

    // 컨버터
    public StudyListDTO ProjectionToDTO(MemberStudyProjectionImpl projection) {
        return customConverters.ProjectionToDTO(projection);
    }
    public StudyListDTO StudyToDto(Study study) {
        return customConverters.StudyToDto(study);
    }
    public MemberStudy MemberStudyDTOToDomain(MemberStudyDTO memberStudyDTO, MemberStudy memberStudy) {
        return customConverters.MemberStudyDTOToDomain(memberStudyDTO, memberStudy);
    }



    @Override
    public List<MemberStudy> findByMemberEmail(String email) {
        return memberStudyRepository.findByMemberEmail(email);
    }
}
