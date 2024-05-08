package com.otl.otl.service;

import com.otl.otl.dto.StudyDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class StudyServiceImpl implements StudyService {
    private final ModelMapper modelMapper;

    @Override
    public Long register(StudyDTO studyDTO) {
        return null;
    }

    @Override
    public StudyDTO readStudy(Long sno) {
        return null;
    }

    @Override
    public void modify(StudyDTO studyDTO) {

    }

    @Override
    public void remove(Long sno) {

    }
}
