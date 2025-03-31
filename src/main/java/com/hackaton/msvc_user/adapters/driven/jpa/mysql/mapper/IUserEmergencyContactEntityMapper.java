package com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper;

import com.hackaton.msvc_user.adapters.driven.jpa.mysql.entity.UserEmergencyContactEntity;
import com.hackaton.msvc_user.domain.model.UserEmergencyContact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {IEmergencyContactEntityMapper.class})
public interface IUserEmergencyContactEntityMapper {
    @Mapping(source = "emergencyContact", target = "emergencyContact")
    @Mapping(target = "user", ignore = true)  // Ignora el mapeo del usuario
    UserEmergencyContact toModel(UserEmergencyContactEntity userEmergencyContactEntity);

    @Mapping(source = "emergencyContact", target = "emergencyContact")
    @Mapping(target = "user", ignore = true)  // Ignora el mapeo del usuario
    UserEmergencyContactEntity toEntity(UserEmergencyContact userEmergencyContact);
}