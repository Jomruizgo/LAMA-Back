package com.hackaton.msvc_user.adapters.driving.http.mapper.request;

import com.hackaton.msvc_user.adapters.driving.http.dto.AddUserRequestDto;
import com.hackaton.msvc_user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface IUserRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    User addRequestDtotoModel(AddUserRequestDto addUserRequestDto);
}
