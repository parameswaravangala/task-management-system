package com.bank.taskmanagement.mapper;

import com.bank.taskmanagement.dto.NotificationTypeDTO;
import com.bank.taskmanagement.entity.NotificationType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface NotificationTypeMapper extends GenericMapper<NotificationType, NotificationTypeDTO> {

}