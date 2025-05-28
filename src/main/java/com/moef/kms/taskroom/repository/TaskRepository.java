package com.moef.kms.taskroom.repository;

import com.moef.kms.taskroom.entity.TaskInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskInfo, Long> {
    List<TaskInfo> findByUserId(String userId);
}
