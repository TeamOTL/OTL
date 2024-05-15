//package com.otl.otl.dto.customDTO;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.otl.otl.dto.InterestsDTO;
//import com.otl.otl.dto.StudyCreateDTO;
//import com.otl.otl.dto.TaskDTO;
//import lombok.*;
//
//import java.util.List;
//
//// StudyCreateCustomDTO 클래스: StudyCreateDTO를 상속받아 customTasks와 customInterests 필드를 추가
//
//@EqualsAndHashCode(callSuper = true)
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class StudyCreateCustomDTO extends StudyCreateDTO {
//
//    @JsonProperty("customTasks")
//    private List<CustomTaskDTO> customTasks; // 사용자 정의 Task 리스트
//
//    @JsonProperty("customInterests")
//    private List<CustomInterestDTO> customInterests; // 사용자 정의 Interest 리스트
//
//    // 수동으로 빌더 패턴 구현
//    public static StudyCreateCustomDTOBuilder builder() {
//        return new StudyCreateCustomDTOBuilder();
//    }
//
//    // 빌더 클래스 정의
//    public static class StudyCreateCustomDTOBuilder {
//        private Long sno;
//        private String studyName;
//        private String studyDescription;
//        private String studyPlan;
//        private Long maxMember;
//        private String firstDate;
//        private String rStartDate = "2024-12-23"; // 기본값 설정
//        private String rEndDate = "2025-12-14"; // 기본값 설정
//        private String DDay;
//
//        private List<InterestsDTO> interests;
//        private List<TaskDTO> tasks;
//        private Long cno;
//        private List<CustomTaskDTO> customTasks;
//        private List<CustomInterestDTO> customInterests;
//
//        public StudyCreateCustomDTOBuilder sno(Long sno) {
//            this.sno = sno;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder studyName(String studyName) {
//            this.studyName = studyName;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder studyDescription(String studyDescription) {
//            this.studyDescription = studyDescription;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder studyPlan(String studyPlan) {
//            this.studyPlan = studyPlan;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder maxMember(Long maxMember) {
//            this.maxMember = maxMember;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder firstDate(String firstDate) {
//            this.firstDate = firstDate;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder rStartDate(String rStartDate) {
//            this.rStartDate = rStartDate;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder rEndDate(String rEndDate) {
//            this.rEndDate = rEndDate;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder DDay(String DDay) {
//            this.DDay = DDay;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder interests(List<InterestsDTO> interests) {
//            this.interests = interests;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder tasks(List<TaskDTO> tasks) {
//            this.tasks = tasks;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder cno(Long cno) {
//            this.cno = cno;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder customTasks(List<CustomTaskDTO> customTasks) {
//            this.customTasks = customTasks;
//            return this;
//        }
//
//        public StudyCreateCustomDTOBuilder customInterests(List<CustomInterestDTO> customInterests) {
//            this.customInterests = customInterests;
//            return this;
//        }
//
//        // 빌드 메서드: StudyCreateCustomDTO 객체를 생성하여 반환
//        public StudyCreateCustomDTO build() {
//            StudyCreateCustomDTO dto = new StudyCreateCustomDTO();
//            dto.setSno(this.sno);
//            dto.setStudyName(this.studyName);
//            dto.setStudyDescription(this.studyDescription);
//            dto.setStudyPlan(this.studyPlan);
//            dto.setMaxMember(this.maxMember);
//            dto.setFirstDate(this.firstDate);
//            dto.setRStartDate(this.rStartDate);
//            dto.setREndDate(this.rEndDate);
//            dto.setDDay(this.DDay); // 필드 이름을 맞추어 설정
//            dto.setInterests(this.interests);
//            dto.setTasks(this.tasks);
//            dto.setCno(this.cno);
//            dto.setCustomTasks(this.customTasks);
//            dto.setCustomInterests(this.customInterests);
//            return dto;
//        }
//    }
//}

package com.otl.otl.dto.customDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.otl.otl.dto.InterestsDTO;
import com.otl.otl.dto.StudyCreateDTO;
import com.otl.otl.dto.StudyDTO;
import com.otl.otl.dto.TaskDTO;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudyCreateCustomDTO extends StudyDTO {

    @JsonProperty("customTasks")
    private List<CustomTaskDTO> customTasks; // 사용자 정의 Task 리스트

    @JsonProperty("customInterests")
    private List<CustomInterestDTO> customInterests; // 사용자 정의 Interest 리스트

//    @Builder
//    public StudyCreateCustomDTO(Long sno, String studyName, String studyDescription, String studyPlan, Long maxMember, String firstDate, String rStartDate, String rEndDate, String dDay, List<InterestsDTO> interestsDTO, List<TaskDTO> taskDTO, Long cno, List<CustomTaskDTO> customTasks, List<CustomInterestDTO> customInterests) {
//        super(sno, studyName, studyDescription, studyPlan, maxMember, firstDate, rStartDate, rEndDate, dDay, interestsDTO, taskDTO, cno);
//        this.customTasks = customTasks;
//        this.customInterests = customInterests;
//    }
}

