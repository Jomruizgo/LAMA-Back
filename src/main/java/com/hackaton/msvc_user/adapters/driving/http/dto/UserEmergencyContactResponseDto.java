package com.hackaton.msvc_user.adapters.driving.http.dto;

import com.hackaton.msvc_user.domain.model.EmergencyContact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserEmergencyContactResponseDto {
    private Long id;
    private EmergencyContact emergencyContact;
    private String relationship;
}
