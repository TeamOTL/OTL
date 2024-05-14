package com.otl.otl.dto.MemberStudyProjection;

import com.otl.otl.domain.Study;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface MemberStudyProjection {
    Long getMsNo();
    Study getStudy();
    String getEmail(); // Member 엔티티의 email 속성
    Boolean getIsAccepted();
    Boolean getIsManaged();
    String getComment();
}