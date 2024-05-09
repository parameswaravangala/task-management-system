package com.bank.taskmanagement.entity;

import com.bank.taskmanagement.constants.Action;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class TaskHistory extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long taskId;
    @Enumerated(EnumType.STRING)
    private Action action;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int priority;
    private LocalDate dueDate;
    @Version
    private int version;

    public static TaskHistoryBuilder builder() {
        return new TaskHistoryBuilder();
    }

    public static class TaskHistoryBuilder{
        private Task task;
        private Action action;
        public TaskHistoryBuilder withTask(Task task) {
            this.task = task;
            return this;
        }

        public TaskHistoryBuilder withAction(Action action) {
            this.action = action;return this;
        }

        public TaskHistory build(){
            TaskHistory taskHistory = new TaskHistory();
            taskHistory.setTaskId(task.getId());
            taskHistory.setTitle(task.getTitle());
            taskHistory.setDescription(task.getDescription());
            taskHistory.setStatus(task.getStatus());
            taskHistory.setPriority(task.getPriority());
            taskHistory.setDueDate(task.getDueDate());
            taskHistory.setAction(action);
            return taskHistory;
        }


    }
}
