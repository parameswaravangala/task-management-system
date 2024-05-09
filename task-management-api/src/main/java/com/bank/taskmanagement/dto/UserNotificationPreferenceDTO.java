package com.bank.taskmanagement.dto;

import com.bank.taskmanagement.entity.NotificationChannel;
import com.bank.taskmanagement.entity.NotificationType;
import com.bank.taskmanagement.entity.User;

public class UserNotificationPreferenceDTO extends AbstractDTO<Integer> {
    private Integer id;
    private User user;
    private NotificationType notificationType;
    private NotificationChannel channel;
    private Boolean isEnabled;

    public UserNotificationPreferenceDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
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

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Boolean getIsEnabled() {
        return this.isEnabled;
    }
}