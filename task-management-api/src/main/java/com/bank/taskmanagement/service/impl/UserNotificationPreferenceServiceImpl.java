package com.bank.taskmanagement.service.impl;

import com.bank.taskmanagement.dao.UserNotificationPreferenceRepository;
import com.bank.taskmanagement.entity.Task;
import com.bank.taskmanagement.entity.UserNotificationPreference;
import com.bank.taskmanagement.service.UserNotificationPreferenceService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserNotificationPreferenceServiceImpl implements UserNotificationPreferenceService {
    private final UserNotificationPreferenceRepository repository;

    public UserNotificationPreferenceServiceImpl(UserNotificationPreferenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserNotificationPreference save(UserNotificationPreference entity) {
        return repository.save(entity);
    }

    @Override
    public List<UserNotificationPreference> save(List<UserNotificationPreference> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserNotificationPreference> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<UserNotificationPreference> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<UserNotificationPreference> findAll(Pageable pageable) {
        Page<UserNotificationPreference> entityPage = repository.findAll(pageable);
        List<UserNotificationPreference> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public UserNotificationPreference update(UserNotificationPreference entity, Integer id) {
        Optional<UserNotificationPreference> optional = findById(id );
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}