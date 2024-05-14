package com.otl.otl.service;

import com.otl.otl.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAcceptedTasksByMemberEmail(String email);
}
