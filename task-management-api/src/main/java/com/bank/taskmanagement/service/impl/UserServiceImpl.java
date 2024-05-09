package com.bank.taskmanagement.service.impl;

import com.bank.taskmanagement.dao.UserRepository;
import com.bank.taskmanagement.entity.Task;
import com.bank.taskmanagement.entity.User;
import com.bank.taskmanagement.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public List<User> save(List<User> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<User> entityPage = repository.findAll(pageable);
        List<User> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public User update(User entity, Integer id) {
        Optional<User> optional = findById(id );
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}