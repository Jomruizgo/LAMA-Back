package com.hackaton.msvc_user.adapters.driven.jpa.mysql.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_emergency_contacts")
@AllArgsConstructor
@NoArgsConstructor
public class UserEmergencyContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore  // Añadido para evitar serialización circular
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "emergency_contact_id", nullable = false)
    private EmergencyContactEntity emergencyContact;

    @Column(name = "relationship", nullable = false)
    private String relationship;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmergencyContactEntity getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContactEntity emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}