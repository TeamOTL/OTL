package com.otl.otl.service;

import com.otl.otl.domain.MemberStudy;
import com.otl.otl.repository.MemberStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberStudyServiceImpl implements MemberStudyService{

    private final MemberStudyRepository memberStudyRepository;

    @Autowired
    public MemberStudyServiceImpl(MemberStudyRepository memberStudyRepository) {
        this.memberStudyRepository = memberStudyRepository;
    }

    @Override
    public List<MemberStudy> findByMemberEmail(String email) {
        return memberStudyRepository.findByMemberEmail(email);
    }

}
