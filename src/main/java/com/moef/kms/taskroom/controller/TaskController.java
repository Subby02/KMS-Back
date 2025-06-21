package com.moef.kms.taskroom.controller;

import com.moef.kms.taskroom.dto.TaskDto;
import com.moef.kms.taskroom.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto) {
        // userId 고정
        taskDto.setUserId("T001");

        // 유효성 검사
        if (taskDto.getTitle() == null || taskDto.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("제목(title)은 필수 입력입니다.");
        }

        if (taskDto.getStartDate() == null) {
            return ResponseEntity.badRequest().body("시작일(startDate)은 필수 입력입니다.");
        }

        taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable String userId) {
        List<TaskDto> tasks = taskService.getTasksByUser(userId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        boolean updated = taskService.updateTask(taskId, taskDto);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        boolean deleted = taskService.deleteTask(taskId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
