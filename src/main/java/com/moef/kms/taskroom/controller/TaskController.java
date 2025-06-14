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
    public ResponseEntity<Void> createTask(@RequestBody TaskDto taskDto) {
        taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable String userId) {
        List<TaskDto> tasks = taskService.getTasksByUser(userId);
        return ResponseEntity.ok(tasks);
    }

    // TaskId로 수정하기 (PUT)
    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        boolean updated = taskService.updateTask(taskId, taskDto);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // TaskId로 삭제하기 (DELETE)
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        boolean deleted = taskService.deleteTask(taskId);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 삭제 성공 시 204 반환
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
