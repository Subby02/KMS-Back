package com.moef.kms.taskroom.controller;

import com.moef.kms.taskroom.dto.TaskDto;
import com.moef.kms.taskroom.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public void createTask(@RequestBody TaskDto taskDto) {
        taskService.createTask(taskDto);
    }

    @GetMapping("/{userId}")
    public List<TaskDto> getTasks(@PathVariable String userId) {
        return taskService.getTasksByUser(userId);
    }
}
