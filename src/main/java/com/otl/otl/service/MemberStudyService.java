package com.otl.otl.service;

import com.otl.otl.domain.MemberStudy;

import java.util.List;

public interface MemberStudyService {

    List<MemberStudy> findByMemberEmail(String email);


}
