package com.otl.otl.service.converter;

import com.otl.otl.domain.*;
import com.otl.otl.dto.InterestsDTO;
import com.otl.otl.dto.MemberStudyDTO;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjectionImpl;
import com.otl.otl.dto.StudyListDTO;
import com.otl.otl.dto.TaskDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomConverters {
    public StudyListDTO ProjectionToDTO(MemberStudyProjectionImpl projection) {
        return StudyListDTO.builder()
                .sno(projection.getStudy().getSno())
                .studyName(projection.getStudy().getStudyName())
                .studyDescription(projection.getStudy().getStudyDescription())
                .studyPlan(projection.getStudy().getStudyPlan())
                .maxMember(projection.getStudy().getMaxMember())
                .firstDate(projection.getStudy().getFirstDate())
                .rStartDate(projection.getStudy().getRStartDate())
                .rEndDate(projection.getStudy().getREndDate())
                .categoryCno(projection.getStudy().getCategory().getCno())
                .categoryName(projection.getStudy().getCategory().getCategoryName())
                .categoryImage(projection.getStudy().getCategory().getCategoryImage())
                .dDay(projection.getStudy().getDDay())
                .tasks(toTaskDTOList(projection.getStudy().getTasks()))
                .interests(toInterestsDTOList(projection.getStudy().getInterests()))
                .build();
    }

    private List<TaskDTO> toTaskDTOList(List<Task> tasks) {
        List<TaskDTO> taskDTOList = new ArrayList<>();
        for (Task task : tasks) {
            TaskDTO taskDTO = TaskDTO.builder()
                    .tno(task.getTno())
                    .taskTitle(task.getTaskTitle())
                    .taskDate(task.getTaskDate())
                    .taskTime(task.getTaskTime())
                    .taskPlace(task.getTaskPlace())
                    .taskMember(task.getTaskMember())
                    .taskContent(task.getTaskContent())
                    .isCompleted(task.isCompleted())
                    .build();
            taskDTOList.add(taskDTO);
        }
        return taskDTOList;
    }

    private List<InterestsDTO> toInterestsDTOList(List<Interests> interests) {
        List<InterestsDTO> interestsDTOList = new ArrayList<>();
        for (Interests interest : interests) {
            InterestsDTO interestsDTO = InterestsDTO.builder()
                    .ino(interest.getIno())
                    .interestName(interest.getInterestName())
                    .build();
            interestsDTOList.add(interestsDTO);
        }
        return interestsDTOList;
    }

    public StudyListDTO StudyToDto(Study study) {
        return StudyListDTO.builder()
                .sno(study.getSno())
                .studyName(study.getStudyName())
                .studyDescription(study.getStudyDescription())
                .studyPlan(study.getStudyPlan())
                .maxMember(study.getMaxMember())
                .firstDate(study.getFirstDate())
                .rStartDate(study.getRStartDate())
                .rEndDate(study.getREndDate())
                .categoryCno(study.getCategory().getCno())
                .categoryName(study.getCategory().getCategoryName())
                .categoryImage(study.getCategory().getCategoryImage())
                .dDay(study.getDDay())
                .tasks(toTaskDTOList(study.getTasks()))
                .interests(toInterestsDTOList(study.getInterests()))
                .build();
    }
    //    public MemberStudy MemberStudyDTOToDomain(MemberStudyDTO memberStudyDTO, MemberStudy memberStudy) {
//        member.builder()
//                .email(memberStudyDTO.getMember().getEmail()
//
//        study.builder()
//                .sno(memberStudyDTO.getStudy().getSno())
//
//        return MemberStudy.builder()
//                .msNo(memberStudyDTO.getMsNo())
//                .member(memberStudyDTO.getMember())
//                .study(memberStudyDTO.getStudy())
//                .isAccepted(memberStudyDTO.isAccepted())
//                .isManaged(memberStudyDTO.isManaged())
//                .comment(memberStudyDTO.getComment())
//                .build();
//    }
    public MemberStudy MemberStudyDTOToDomain(MemberStudyDTO memberStudyDTO, MemberStudy memberStudy) {
        Member member = Member.builder().email(memberStudyDTO.getMember().getEmail()).build(); // DTO에서 멤버의 이메일 값을 가져와서 Member 엔티티를 생성
        Study study = Study.builder().sno(memberStudyDTO.getStudy().getSno()).build(); // DTO에서 스터디 고유번호 값을 가져와서 Study 엔티티를 생성

        return MemberStudy.builder()
                .msNo(memberStudyDTO.getMsNo())
                .member(member)
                .study(study)
                .isAccepted(memberStudyDTO.isAccepted())
                .isManaged(memberStudyDTO.isManaged())
                .comment(memberStudyDTO.getComment())
                .build();
    }


}
