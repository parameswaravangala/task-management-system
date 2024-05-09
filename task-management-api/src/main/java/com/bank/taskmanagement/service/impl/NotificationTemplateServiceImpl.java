package com.bank.taskmanagement.service.impl;

import com.bank.taskmanagement.dao.NotificationTemplateRepository;
import com.bank.taskmanagement.entity.NotificationTemplate;
import com.bank.taskmanagement.entity.Task;
import com.bank.taskmanagement.service.NotificationTemplateService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NotificationTemplateServiceImpl implements NotificationTemplateService {
    private final NotificationTemplateRepository repository;

    public NotificationTemplateServiceImpl(NotificationTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public NotificationTemplate save(NotificationTemplate entity) {
        return repository.save(entity);
    }

    @Override
    public List<NotificationTemplate> save(List<NotificationTemplate> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<NotificationTemplate> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<NotificationTemplate> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<NotificationTemplate> findAll(Pageable pageable) {
        Page<NotificationTemplate> entityPage = repository.findAll(pageable);
        List<NotificationTemplate> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public NotificationTemplate update(NotificationTemplate entity, Long id) {
        Optional<NotificationTemplate> optional = findById(id );
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}