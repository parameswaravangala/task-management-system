package com.bank.taskmanagement.service.impl;

import com.bank.taskmanagement.dao.NotificationChannelRepository;
import com.bank.taskmanagement.entity.NotificationChannel;
import com.bank.taskmanagement.service.NotificationChannelService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NotificationChannelServiceImpl implements NotificationChannelService {
    private final NotificationChannelRepository repository;

    public NotificationChannelServiceImpl(NotificationChannelRepository repository) {
        this.repository = repository;
    }

    @Override
    public NotificationChannel save(NotificationChannel entity) {
        return repository.save(entity);
    }

    @Override
    public List<NotificationChannel> save(List<NotificationChannel> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<NotificationChannel> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<NotificationChannel> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<NotificationChannel> findAll(Pageable pageable) {
        Page<NotificationChannel> entityPage = repository.findAll(pageable);
        List<NotificationChannel> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public NotificationChannel update(NotificationChannel entity, Integer id) {
        Optional<NotificationChannel> optional = findById(id );
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}