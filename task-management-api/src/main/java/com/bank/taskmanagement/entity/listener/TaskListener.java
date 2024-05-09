package com.bank.taskmanagement.entity.listener;

import com.bank.taskmanagement.constants.Action;
import com.bank.taskmanagement.dao.TaskHistoryRepository;
import com.bank.taskmanagement.entity.TaskHistory;
import com.bank.taskmanagement.service.NotificationService;
import com.bank.taskmanagement.entity.Task;
import com.bank.taskmanagement.util.BeanUtil;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.bank.taskmanagement.constants.Action.*;
import static jakarta.transaction.Transactional.TxType.MANDATORY;

@Component
@RequiredArgsConstructor
public class TaskListener {

    @PostPersist
    @Transactional(MANDATORY)
    public void prePersist(Task target) {
        save(target, INSERTED);
    }

    @PreUpdate
    @Transactional(MANDATORY)
    public void preUpdate(Task target) {
        save(target, UPDATED);
    }

    @PreRemove
    @Transactional(MANDATORY)
    public void preRemove(Task target) {
        save(target, DELETED);
    }

    private void save(Task target, Action action) {
        BeanUtil.getBean(EntityManager.class).persist(TaskHistory.builder().withTask(target).withAction(action).build());
    }
}
