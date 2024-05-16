package com.otl.otl.service;

import com.otl.otl.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAcceptedTasksByMemberEmail(String email);

    List<Task> getManaedTasksByMemberEmail(String email);
    void deleteTask(Long sno, Long tno); // 추가된 메서드

    void updateTask(Long tno, String taskTitle, String taskDate, String taskTime, String taskPlace, String taskMember, String taskContent);
}
