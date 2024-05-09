package com.bank.taskmanagement.dto;

import com.bank.taskmanagement.entity.NotificationChannel;
import com.bank.taskmanagement.entity.NotificationType;

import java.time.Instant;

public class NotificationTemplateDTO extends AbstractDTO<Long> {
    private Long id;
    private String title;
    private String description;
    private NotificationType notificationType;
    private NotificationChannel channel;
    private Instant createdAt;
    private Instant updatedAt;
    private String content;

    public NotificationTemplateDTO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationType getNotificationType() {
        return this.notificationType;
    }

    public void setChannel(NotificationChannel channel) {
        this.channel = channel;
    }

    public NotificationChannel getChannel() {
        return this.channel;
    }

    public void setCreatedAt(java.time.Instant createdAt) {
        this.createdAt = createdAt;
    }

    public java.time.Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setUpdatedAt(java.time.Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public java.time.Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
}