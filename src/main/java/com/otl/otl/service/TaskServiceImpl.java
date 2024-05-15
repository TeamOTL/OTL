package com.otl.otl.service;

import com.otl.otl.domain.Task;
import com.otl.otl.repository.TaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAcceptedTasksByMemberEmail(String email) {
        return taskRepository.findTaskByMemberEmailAndIsAccepted(email);
    }

    @Override
    public List<Task> getManaedTasksByMemberEmail(String email) {
        return taskRepository.findTaskByMemberEmailAndIsManaed(email);
    }
}
