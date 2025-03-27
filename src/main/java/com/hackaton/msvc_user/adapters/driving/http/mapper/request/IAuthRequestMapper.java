package com.hackaton.msvc_user.adapters.driving.http.mapper.request;

import com.hackaton.msvc_user.adapters.driving.http.dto.AuthenticationRequestDto;
import com.hackaton.msvc_user.domain.model.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAuthRequestMapper {

    @Mapping(target = "token", ignore = true)
    AuthUser authRequestToModel(AuthenticationRequestDto authenticationRequestDto);
}
