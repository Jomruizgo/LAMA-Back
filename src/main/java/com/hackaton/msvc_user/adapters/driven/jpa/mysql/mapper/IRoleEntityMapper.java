package com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper;


import com.hackaton.msvc_user.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.hackaton.msvc_user.domain.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleEntityMapper {
    Role toModel(RoleEntity roleEntity);

    RoleEntity toEntity(Role role);
}
