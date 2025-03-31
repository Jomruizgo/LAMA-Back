package com.hackaton.msvc_user.adapters.driving.http.mapper.response;

import com.hackaton.msvc_user.adapters.driving.http.dto.UserResponseDto;
import com.hackaton.msvc_user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserResponseMapper {
    UserResponseDto toUserResponseDto(User user);
    List<UserResponseDto> toUserResponseDtoList(List<User> users);
}
