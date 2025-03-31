package com.hackaton.msvc_user.domain.model;

import com.hackaton.msvc_user.domain.util.Rank;
import com.hackaton.msvc_user.domain.util.Status;
import com.hackaton.msvc_user.domain.util.UserRole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private Long id;
    private Long documentId;
    private String name;
    private String lastName;
    private String mobileNumber;
    private Date birthdate;
    private String email;
    private String password;
    //private UserRole role;
    private Status status;
    private Date createdAt;
    private Rank rank;
    private String rh;
    private String eps;
    private Long sponsorId;
    private String address;
    private String city;
    private List<UserEmergencyContact> userEmergencyContacts = new ArrayList<>();

    public User() {}

    public User(Long id, Long documentId, String name, String lastName, String mobileNumber, Date birthdate, String email, String password, Status status, Date createdAt, Rank rank, String rh, String eps, Long sponsorId, String address, String city, List<UserEmergencyContact> userEmergencyContacts) {
        this.id = id;
        this.documentId = documentId;
        this.name = name;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.status = status;
        this.createdAt = createdAt;
        this.rank = rank;
        this.rh = rh;
        this.eps = eps;
        this.sponsorId = sponsorId;
        this.address = address;
        this.city = city;
        this.userEmergencyContacts = userEmergencyContacts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean getIsActive() { return status == Status.ACTIVE; }

    /**
     * Obtiene el rol del usuario en función de su rango.
     */
    public UserRole getRole() {
        return rank != null ? rank.getRole() : UserRole.CLIENT; // Si no tiene rank, se asume CLIENT
    }
}
