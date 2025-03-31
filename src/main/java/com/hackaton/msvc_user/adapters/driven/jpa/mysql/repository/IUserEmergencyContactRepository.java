package com.hackaton.msvc_user.adapters.driven.jpa.mysql.repository;

import com.hackaton.msvc_user.adapters.driven.jpa.mysql.entity.UserEmergencyContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserEmergencyContactRepository extends JpaRepository<UserEmergencyContactEntity, Long> {
    List<UserEmergencyContactEntity> findByUserId(Long userId);
}
