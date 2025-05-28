package com.moef.kms.taskroom.service;

import com.moef.kms.taskroom.dto.TaskDto;
import com.moef.kms.taskroom.entity.TaskInfo;
import com.moef.kms.taskroom.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void createTask(TaskDto dto) {
        TaskInfo task = new TaskInfo();
        task.setUserId(dto.getUserId());
        task.setTitle(dto.getTitle());
        task.setStartDate(dto.getStartDate());
        task.setEndDate(dto.getEndDate());
        task.setAlarmSet(dto.isAlarmSet());
        task.setVisibleSet(dto.isVisibleSet());
        taskRepository.save(task);
    }

    public List<TaskDto> getTasksByUser(String userId) {
        return taskRepository.findByUserId(userId).stream().map(task -> {
            TaskDto dto = new TaskDto();
            dto.setUserId(task.getUserId());
            dto.setTitle(task.getTitle());
            dto.setStartDate(task.getStartDate());
            dto.setEndDate(task.getEndDate());
            dto.setAlarmSet(task.isAlarmSet());
            dto.setVisibleSet(task.isVisibleSet());
            return dto;
        }).collect(Collectors.toList());
    }
}
