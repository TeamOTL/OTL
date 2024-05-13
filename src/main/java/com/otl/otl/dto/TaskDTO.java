package com.otl.otl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDTO {
    private int tno;
    private int taskWeek;
    private String taskTitle;
    private String taskDate;
    private String taskTime;
    private String taskPlace;
    private String taskMember;
    private String taskContent;
    private boolean isCompleted;
    private String planDate;

//    public TaskDTO(int tno, int taskWeek, String taskTitle) {
//    }

}
