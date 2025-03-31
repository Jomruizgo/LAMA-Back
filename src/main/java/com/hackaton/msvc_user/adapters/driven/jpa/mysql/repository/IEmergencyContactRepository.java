package com.hackaton.msvc_user.adapters.driven.jpa.mysql.repository;

import com.hackaton.msvc_user.adapters.driven.jpa.mysql.entity.EmergencyContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmergencyContactRepository extends JpaRepository<EmergencyContactEntity, Long> {
}
