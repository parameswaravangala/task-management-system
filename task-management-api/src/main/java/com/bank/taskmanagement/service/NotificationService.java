package com.bank.taskmanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationService {
    public void sendNotification(){
        log.info("Sending Notification");
    }
}
