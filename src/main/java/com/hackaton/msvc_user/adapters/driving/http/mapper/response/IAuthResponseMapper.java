package com.hackaton.msvc_user.adapters.driving.http.mapper.response;

import com.hackaton.msvc_user.adapters.driving.http.dto.AuthenticationResponseDto;
import com.hackaton.msvc_user.domain.model.AuthUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAuthResponseMapper {
    AuthenticationResponseDto modelToAuthResponse(AuthUser authUser);
}
