package com.bank.taskmanagement.mapper;

import com.bank.taskmanagement.dto.TaskDTO;
import com.bank.taskmanagement.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface TaskMapper extends GenericMapper<Task, TaskDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Task asEntity(TaskDTO dto);
}