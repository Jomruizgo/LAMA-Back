package com.hackaton.msvc_user.adapters.driven.jpa.mysql.adapter;

import com.hackaton.msvc_user.adapters.driven.jpa.mysql.entity.UserEmergencyContactEntity;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.entity.UserEntity;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper.IUserEmergencyContactEntityMapper;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.mapper.PaginationMapper;
import com.hackaton.msvc_user.adapters.driven.jpa.mysql.repository.IUserRepository;
import com.hackaton.msvc_user.domain.model.PaginationModel;
import com.hackaton.msvc_user.domain.model.User;
import com.hackaton.msvc_user.domain.model.UserEmergencyContact;
import com.hackaton.msvc_user.domain.spi.IUserPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class UserAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IUserEmergencyContactEntityMapper userEmergencyContactEntityMapper;

    public UserAdapter(IUserRepository userRepository, IUserEntityMapper userEntityMapper, IUserEmergencyContactEntityMapper userEmergencyContactEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.userEmergencyContactEntityMapper = userEmergencyContactEntityMapper;
    }

    @Override
    public void save(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public User findUserByEmail(String email) {
        return userEntityMapper.toModel(userRepository.findByEmail(email).orElse(null));
    }

    @Override
    public PaginationModel<User> listUsers(String sortBy, boolean ascending, int page, int size) {
        Sort sort= Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<UserEntity> userPage = userRepository.findAll(pageRequest);

        return PaginationMapper.toUserPaginationModel(userPage, userEntityMapper);
    }

    @Override
    public User findUserByDocumentId(Long documentId) {
        return userEntityMapper.toModel(userRepository.findByDocumentId(documentId).orElse(null));
    }

    @Override
    public User findUserById(Long userId) {
        return userEntityMapper.toModel(userRepository.findById(userId).orElse(null));
    }

    @Override
    public void updateUser(Long userId, User user) {
        userRepository.findById(userId).ifPresent(entity -> {
            updateBasicUserInfo(entity, user);
            updateEmergencyContacts(entity, user.getUserEmergencyContacts());
            userRepository.save(entity);
        });
    }

    private void updateBasicUserInfo(UserEntity entity, User user) {
        // Se usa el mapper para convertir el modelo a la entidad
        UserEntity updatedEntity = userEntityMapper.toEntity(user);

        // Se actualizan los campos en la entidad existente para evitar perder datos no modificados
        entity.setDocumentId(updatedEntity.getDocumentId());
        entity.setName(updatedEntity.getName());
        entity.setLastName(updatedEntity.getLastName());
        entity.setMobileNumber(updatedEntity.getMobileNumber());
        entity.setBirthdate(updatedEntity.getBirthdate());
        entity.setEmail(updatedEntity.getEmail());
        entity.setStatus(updatedEntity.getStatus());
        entity.setRank(updatedEntity.getRank());
        entity.setPassword(updatedEntity.getPassword()); // Asegurar cifrado si es necesario
        entity.setRh(updatedEntity.getRh());
        entity.setEps(updatedEntity.getEps());
        entity.setSponsorId(updatedEntity.getSponsorId());
        entity.setAddress(updatedEntity.getAddress());
        entity.setCity(updatedEntity.getCity());
    }

    private void updateEmergencyContacts(UserEntity entity, List<UserEmergencyContact> newContacts) {
        if (newContacts != null || !newContacts.isEmpty()) { // Solo actualiza si se envían nuevos contactos
            List<UserEmergencyContactEntity> contactEntities = newContacts.stream()
                    .map(userEmergencyContactEntityMapper::toEntity)
                    .peek(contact -> contact.setUser(entity))
                    .collect(Collectors.toList());

            entity.getUserEmergencyContacts().clear();
            entity.getUserEmergencyContacts().addAll(contactEntities);
        }
        // Si newContacts es null, no hacemos nada y se mantienen los contactos existentes
    }


    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresent(entity -> {
            // Optionally, you can set the status to inactive instead of deleting
            // entity.setStatus(User.Status.INACTIVE);
            // userRepository.save(entity);
            userRepository.delete(entity);
        });
    }

}
