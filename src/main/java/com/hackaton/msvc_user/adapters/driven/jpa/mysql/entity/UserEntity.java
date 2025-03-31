package com.hackaton.msvc_user.adapters.driven.jpa.mysql.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hackaton.msvc_user.domain.util.Constants;
import com.hackaton.msvc_user.domain.util.Rank;
import com.hackaton.msvc_user.domain.util.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_id",unique = true,nullable = false)
    private Long documentId;

    private String name;

    private String lastName;

    @Column(name = "mobile_number", length = Constants.MAX_MOBILE_NUMBER_LENGTH)
    private String mobileNumber;

    private Date birthdate;

    @Column(name = "email",unique = true,nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "user_rank", nullable = false)
    private String rank;

    @Column(name = "rh", nullable = false)
    private String rh;

    @Column(name = "eps", nullable = false)
    private String eps;

    @Column(name = "sponsor", nullable = false)
    private Long sponsorId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEmergencyContactEntity> userEmergencyContacts = new ArrayList<>();

    //These methods are generated manually because Lombok change their names and this cause mappers issues.
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt;  }

    public List<UserEmergencyContactEntity> getUserEmergencyContacts() {
        return userEmergencyContacts;
    }

    public void setUserEmergencyContacts(List<UserEmergencyContactEntity> userEmergencyContacts) {
        this.userEmergencyContacts = userEmergencyContacts;
    }
}
