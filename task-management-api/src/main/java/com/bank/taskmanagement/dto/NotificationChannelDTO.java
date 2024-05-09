package com.bank.taskmanagement.dto;

public class NotificationChannelDTO extends AbstractDTO<Integer> {
    private Integer id;
    private String name;

    public NotificationChannelDTO() {
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
}