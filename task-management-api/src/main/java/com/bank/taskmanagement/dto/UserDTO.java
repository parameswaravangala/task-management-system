package com.bank.taskmanagement.dto;

import com.bank.taskmanagement.entity.UserNotificationPreference;

import java.util.Set;

public class UserDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String email;
    private String userName;
    private String mobile;
    private String firstName;
    private String lastName;
    private Set<UserNotificationPreference> userNotificationPreferences;

    public UserDTO() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setUserNotificationPreferences(Set<UserNotificationPreference> userNotificationPreferences) {
        this.userNotificationPreferences = userNotificationPreferences;
    }

    public Set<UserNotificationPreference> getUserNotificationPreferences() {
        return this.userNotificationPreferences;
    }
}