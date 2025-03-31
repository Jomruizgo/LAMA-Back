package com.hackaton.msvc_user.adapters.driving.http.dto;

import com.hackaton.msvc_user.domain.model.UserEmergencyContact;
import com.hackaton.msvc_user.domain.util.Rank;
import com.hackaton.msvc_user.domain.util.Status;
import com.hackaton.msvc_user.domain.util.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponseDto {
    private Long id;
    private Long documentId;
    private String name;
    private String lastName;
    private String mobileNumber;
    private Date birthdate;
    private String email;
    private UserRole role;
    private Status status;
    private Date createdAt;
    private Rank rank;
    private String rh;
    private String eps;
    private Long sponsorId;
    private String address;
    private String city;
    private List<UserEmergencyContactResponseDto> userEmergencyContacts = new ArrayList<>();
}
