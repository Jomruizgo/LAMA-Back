package com.hackaton.msvc_user.configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {
        clearDatabase(); // 1. Elimina los datos previos
        insertEmergencyContacts();
        insertUsers();
        insertUserEmergencyContacts();
    }

    private void clearDatabase() {
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM user_emergency_contacts").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM users").executeUpdate();
        entityManager.createNativeQuery("DELETE FROM emergency_contacts").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE user_emergency_contacts AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE users AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE emergency_contacts AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }

    private void insertEmergencyContacts() {
        entityManager.createNativeQuery("INSERT INTO emergency_contacts (id, name, phone_number) VALUES (1, 'Pedro', '3001234567')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO emergency_contacts (id, name, phone_number) VALUES (2, 'Luisa', '3002345678')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO emergency_contacts (id, name, phone_number) VALUES (3, 'Andrés', '3003456789')").executeUpdate();
    }

    private void insertUsers() {
        entityManager.createNativeQuery("""
            INSERT INTO users (id, document_id, name, last_name, mobile_number, birthdate, email, password, status, created_at, user_rank, rh, eps, sponsor, address, city) 
            VALUES (1, 1001002001, 'John', 'Doe', '5551234567', '1990-05-15', 'john.doe@example.com', 
            '$2y$10$gMPPMMtUrYxjoakPQCLnUeA/w/L.bFEcugcnUwimo5kCx7yhIZmeq', 'Activo', '2024-03-30T10:00:00', 
            'Presidente', 'O+', 'HealthPlus', 0, '123 Main St', 'New York')
        """).executeUpdate();

        entityManager.createNativeQuery("""
            INSERT INTO users (id, document_id, name, last_name, mobile_number, birthdate, email, password, status, created_at, user_rank, rh, eps, sponsor, address, city) 
            VALUES (2, 1001002002, 'Jane', 'Smith', '5559876543', '1995-08-22', 'jane.smith@example.com', 
            '$2y$10$gMPPMMtUrYxjoakPQCLnUeA/w/L.bFEcugcnUwimo5kCx7yhIZmeq', 'Trasladado', '2024-03-29T15:30:00', 
            'Rockets', 'A-', 'MedCare', 1, '456 Elm St', 'Los Angeles')
        """).executeUpdate();

        entityManager.createNativeQuery("""
            INSERT INTO users (id, document_id, name, last_name, mobile_number, birthdate, email, password, status, created_at, user_rank, rh, eps, sponsor, address, city) 
            VALUES (3, 1001002003, 'Alice', 'Brown', '5555678901', '1987-12-10', 'alice.brown@example.com', 
            '$2y$10$gMPPMMtUrYxjoakPQCLnUeA/w/L.bFEcugcnUwimo5kCx7yhIZmeq', 'Activo', '2024-03-28T09:45:00', 
            'Tesorero', 'B+', 'SafeLife', 1, '789 Oak St', 'Chicago')
        """).executeUpdate();
    }

    private void insertUserEmergencyContacts() {
        entityManager.createNativeQuery("INSERT INTO user_emergency_contacts (user_id, emergency_contact_id, relationship) VALUES (1, 1, 'Father')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO user_emergency_contacts (user_id, emergency_contact_id, relationship) VALUES (1, 2, 'Mother')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO user_emergency_contacts (user_id, emergency_contact_id, relationship) VALUES (2, 1, 'Father')").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO user_emergency_contacts (user_id, emergency_contact_id, relationship) VALUES (3, 1, 'Mother')").executeUpdate();
    }
}



