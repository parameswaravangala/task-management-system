package com.bank.taskmanagement.service.impl;

import com.bank.taskmanagement.dao.TaskRepository;
import com.bank.taskmanagement.entity.Task;
import com.bank.taskmanagement.exception.ResourceAlreadyExistsException;
import com.bank.taskmanagement.exception.ResourceNotFoundException;
import com.bank.taskmanagement.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task save(Task entity) {
        try {
            return repository.save(entity);
        }catch (DataIntegrityViolationException e){
            log.trace("Exception occurred while saving entity", e);
           throw new ResourceAlreadyExistsException(e.getRootCause().getMessage());
        }catch (Exception e){
            log.trace("Exception occurred while saving entity", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Task> save(List<Task> entities) {
        return  repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        Page<Task> entityPage = repository.findAll(pageable);
        List<Task> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Task update(Task entity, Long id) {
        Optional<Task> optional = findById(id);
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }

    @Override
    public Page<Task> findAll(Specification<Task> taskSpecification, Pageable pageable) {
         Page<Task> searchResults =  repository.findAll(taskSpecification,pageable);
         if (searchResults.isEmpty()) {
             throw new ResourceNotFoundException("No data found for given search criteria");
         }
         return searchResults;
    }
}