package com.otl.otl.dto.MemberStudyProjection;

import com.otl.otl.domain.Study;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberStudyProjectionImpl implements MemberStudyProjection {

    private Long msNo;
    private Study study;
    private String email;
    private Boolean isAccepted;
    private Boolean isManaged;
    private String comment;

    public MemberStudyProjectionImpl(Long msNo, Study study, String email, Boolean isAccepted, Boolean isManaged, String comment) {
        this.msNo = msNo;
        this.study = study;
        this.email = email;
        this.isAccepted = isAccepted;
        this.isManaged = isManaged;
        this.comment = comment;
    }

    @Override
    public Long getMsNo() {
        return msNo;
    }

    @Override
    public Study getStudy() {
        return study;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Boolean getIsAccepted() {
        return isAccepted;
    }

    @Override
    public Boolean getIsManaged() {
        return isManaged;
    }

    @Override
    public String getComment() {
        return comment;
    }
}
