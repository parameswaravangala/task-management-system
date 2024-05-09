package com.bank.taskmanagement.mapper;

import com.bank.taskmanagement.dto.NotificationChannelDTO;
import com.bank.taskmanagement.entity.NotificationChannel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface NotificationChannelMapper extends GenericMapper<NotificationChannel, NotificationChannelDTO> {

}