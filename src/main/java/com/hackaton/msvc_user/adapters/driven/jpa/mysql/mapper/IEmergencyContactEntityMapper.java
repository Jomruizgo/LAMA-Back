package com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper;

import com.hackaton.msvc_user.adapters.driven.jpa.mysql.entity.EmergencyContactEntity;
import com.hackaton.msvc_user.domain.model.EmergencyContact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEmergencyContactEntityMapper {
    EmergencyContact toModel(EmergencyContactEntity emergencyContactEntity);
    EmergencyContactEntity toEntity(EmergencyContact emergencyContact);
}
