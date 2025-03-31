package com.hackaton.msvc_user.adapters.driving.http.dto;


import com.hackaton.msvc_user.domain.model.UserEmergencyContact;
import com.hackaton.msvc_user.domain.util.Rank;
import com.hackaton.msvc_user.domain.util.Status;
import com.hackaton.msvc_user.domain.util.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class UpdateUserRequestDto {

    private Long documentId;

    private String name;

    private String lastName;

    private String mobileNumber;

    private Date birthdate;

    private String email;

    private Status status;

    private Rank rank;

    @NotBlank(message = ValidationMessages.PASSWORD_MANDATORY)
    @Size(min = 8, message = ValidationMessages.MIN_PASSWORD_SIZE)
    private String password;

    private String rh;
    private String eps;
    private Long sponsorId;
    private String address;
    private String city;
    private List<UserEmergencyContact> userEmergencyContacts;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public Long getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(Long sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<UserEmergencyContact> getUserEmergencyContacts() {
        return userEmergencyContacts;
    }

    public void setUserEmergencyContacts(List<UserEmergencyContact> userEmergencyContacts) {
        this.userEmergencyContacts = userEmergencyContacts;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
