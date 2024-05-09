package com.bank.taskmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDto {
    private String object;
    private String field;
    private String message;
    private Object rejectedValue;



}