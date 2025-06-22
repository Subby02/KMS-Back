package com.moef.kms.document.service;

import com.moef.kms.taskroom.dto.TaskDto;
import com.moef.kms.taskroom.entity.TaskInfo;
import com.moef.kms.taskroom.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    private TaskInfo toEntity(TaskDto dto) {
        TaskInfo task = new TaskInfo();
        task.setUserId(dto.getUserId());
        task.setTitle(dto.getTitle());
        task.setStartDate(dto.getStartDate());
        task.setEndDate(dto.getEndDate());
        task.setAlarmSet(dto.isAlarmSet());
        task.setVisibleSet(dto.isVisibleSet());
        return task;
    }

    private TaskDto toDto(TaskInfo task) {
        TaskDto dto = new TaskDto();
        dto.setUserId(task.getUserId());
        dto.setTitle(task.getTitle());
        dto.setStartDate(task.getStartDate());
        dto.setEndDate(task.getEndDate());
        dto.setAlarmSet(task.isAlarmSet());
        dto.setVisibleSet(task.isVisibleSet());
        return dto;
    }

    public void createTask(TaskDto dto) {
        TaskInfo task = toEntity(dto);
        taskRepository.save(task);
    }

    public List<TaskDto> getTasksByUser(String userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 수정: taskId로 조회 후 값 변경, 저장
    public boolean updateTask(Long taskId, TaskDto dto) {
        Optional<TaskInfo> optTask = taskRepository.findById(taskId);
        if (optTask.isPresent()) {
            TaskInfo task = optTask.get();
            // 필요한 값만 변경 (userId는 안 바꾸는 게 일반적)
            task.setTitle(dto.getTitle());
            task.setStartDate(dto.getStartDate());
            task.setEndDate(dto.getEndDate());
            task.setAlarmSet(dto.isAlarmSet());
            task.setVisibleSet(dto.isVisibleSet());
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    // 삭제: taskId가 존재하면 삭제
    public boolean deleteTask(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return true;
        }
        return false;
    }
}
