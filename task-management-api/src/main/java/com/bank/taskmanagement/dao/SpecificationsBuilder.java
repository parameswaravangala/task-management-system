package com.bank.taskmanagement.dao;

import com.bank.taskmanagement.util.SearchCriteria;
import com.bank.taskmanagement.entity.Task;
import com.bank.taskmanagement.util.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationsBuilder {

    private final List<SearchCriteria> criteriaList = new ArrayList<>();

    public SpecificationsBuilder with(
      String key, String operation, Object value, String prefix, String suffix) {
    
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            criteriaList.add(new SearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification<Task> build() {
        if (criteriaList.isEmpty()) {
            return null;
        }

        Specification<Task> result =  TaskSpecification.builder().criteria(criteriaList.get(0)).build();
     
        for (int i = 1; i < criteriaList.size(); i++) {
            result = criteriaList.get(i).isOrPredicate()
              ? Specification.where(result).or(TaskSpecification.builder().criteria(criteriaList.get(0)).build())
              : Specification.where(result).and(TaskSpecification.builder().criteria(criteriaList.get(0)).build());
        }

        return result;
    }
}