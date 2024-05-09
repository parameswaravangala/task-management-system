package com.bank.taskmanagement.mapper;

import com.bank.taskmanagement.dto.UserDTO;
import com.bank.taskmanagement.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface UserMapper extends GenericMapper<User, UserDTO> {

}