package com.bank.taskmanagement.dao;

import com.bank.taskmanagement.entity.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Integer> {
}