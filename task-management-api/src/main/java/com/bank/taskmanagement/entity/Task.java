package com.bank.taskmanagement.entity;

import com.bank.taskmanagement.entity.listener.TaskListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@EntityListeners({AuditingEntityListener.class, TaskListener.class})
@Table(name = "TASK")
public class Task extends AbstractEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int priority;
    private LocalDate dueDate;

    @Override
    public Long getId() {
        return id;
    }
}
