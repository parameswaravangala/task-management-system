package com.bank.taskmanagement.service.impl;

import com.bank.taskmanagement.dao.NotificationTypeRepository;
import com.bank.taskmanagement.entity.NotificationType;
import com.bank.taskmanagement.entity.Task;
import com.bank.taskmanagement.service.NotificationTypeService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NotificationTypeServiceImpl implements NotificationTypeService {
    private final NotificationTypeRepository repository;

    public NotificationTypeServiceImpl(NotificationTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public NotificationType save(NotificationType entity) {
        return repository.save(entity);
    }

    @Override
    public List<NotificationType> save(List<NotificationType> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<NotificationType> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<NotificationType> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<NotificationType> findAll(Pageable pageable) {
        Page<NotificationType> entityPage = repository.findAll(pageable);
        List<NotificationType> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public NotificationType update(NotificationType entity, Integer id) {
        Optional<NotificationType> optional = findById(id );
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}