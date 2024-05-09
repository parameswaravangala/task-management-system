package com.bank.taskmanagement.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class SearchCriteria {
    private final String key;
    private final SearchOperation operation;
    private final Object value;
    private boolean orPredicate;
}