package com.bank.taskmanagement.dto;

import com.bank.taskmanagement.entity.NotificationTemplate;

import java.util.Set;

public class NotificationTypeDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String name;
    private Set<NotificationTemplate> notificationTemplates;

    public NotificationTypeDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setNotificationTemplates(java.util.Set<NotificationTemplate> notificationTemplates) {
        this.notificationTemplates = notificationTemplates;
    }

    public java.util.Set<NotificationTemplate> getNotificationTemplates() {
        return this.notificationTemplates;
    }
}