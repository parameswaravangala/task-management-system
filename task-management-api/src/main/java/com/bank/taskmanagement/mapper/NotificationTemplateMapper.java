package com.bank.taskmanagement.mapper;

import com.bank.taskmanagement.dto.NotificationTemplateDTO;
import com.bank.taskmanagement.entity.NotificationTemplate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface NotificationTemplateMapper extends GenericMapper<NotificationTemplate, NotificationTemplateDTO> {

}