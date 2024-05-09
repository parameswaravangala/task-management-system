package com.bank.taskmanagement.mapper;

import com.bank.taskmanagement.dto.UserNotificationPreferenceDTO;
import com.bank.taskmanagement.entity.UserNotificationPreference;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface UserNotificationPreferenceMapper extends GenericMapper<UserNotificationPreference, UserNotificationPreferenceDTO> {
}