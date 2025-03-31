package com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper;

import com.hackaton.msvc_user.adapters.driven.jpa.mysql.entity.EmergencyContactEntity;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.hackaton.msvc_user.domain.model.EmergencyContact;
import com.hackaton.msvc_user.domain.model.User;
import com.hackaton.msvc_user.domain.model.UserEmergencyContact;
import com.hackaton.msvc_user.domain.util.Rank;
import com.hackaton.msvc_user.domain.util.Status;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {IEmergencyContactEntityMapper.class, StatusRankMapper.class})
public interface IUserEntityMapper {

    @Mapping(target = "userEmergencyContacts", ignore = true)  // Ignoramos para mapeo automático
    @Mapping(target = "status", source = "status", qualifiedByName = "stringToStatus")
    @Mapping(target = "rank", source = "rank", qualifiedByName = "stringToRank")
    User toModel(UserEntity userEntity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "userEmergencyContacts", ignore = true)  // Ignoramos para mapeo automático
    UserEntity toEntity(User user);

    List<User> toModelList(List<UserEntity> userEntities);

    @AfterMapping
    default void setCreateAt(User user, @MappingTarget UserEntity userEntity) {
        if (userEntity.getCreatedAt() == null) {
            userEntity.setCreatedAt(LocalDateTime.now());
        }
    }

    // Agregar método para mapeo manual de contactos de emergencia
    @AfterMapping
    default void mapUserEmergencyContacts(UserEntity userEntity, @MappingTarget User user) {
        if (userEntity.getUserEmergencyContacts() != null) {
            List<UserEmergencyContact> contacts = userEntity.getUserEmergencyContacts().stream()
                    .map(contactEntity -> {
                        UserEmergencyContact contact = new UserEmergencyContact();
                        contact.setId(contactEntity.getId());
                        contact.setRelationship(contactEntity.getRelationship());
                        // Utiliza el mapper de contactos de emergencia
                        contact.setEmergencyContact(new IEmergencyContactEntityMapper() {
                            @Override
                            public EmergencyContact toModel(EmergencyContactEntity entity) {
                                EmergencyContact ec = new EmergencyContact();
                                ec.setId(entity.getId());
                                ec.setName(entity.getName());
                                ec.setPhoneNumber(entity.getPhoneNumber());
                                return ec;
                            }

                            @Override
                            public EmergencyContactEntity toEntity(EmergencyContact model) {
                                return null; // No necesitamos esta dirección para este caso
                            }
                        }.toModel(contactEntity.getEmergencyContact()));

                        // NO establecemos el usuario de vuelta para evitar ciclos
                        return contact;
                    })
                    .collect(Collectors.toList());

            user.setUserEmergencyContacts(contacts);
        }
    }

}
