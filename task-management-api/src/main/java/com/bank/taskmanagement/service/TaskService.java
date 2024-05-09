package com.bank.taskmanagement.service;


import com.bank.taskmanagement.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface TaskService extends GenericService<Task, Long> {
    Page<Task> findAll(Specification<Task> taskSpecification, Pageable pageable);
}