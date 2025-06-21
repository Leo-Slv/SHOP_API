package com.example.SHOP_API.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID id;

    // Campos obrigatórios
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username cannot be empty")
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @NotNull(message = "Surname is required")
    @NotBlank(message = "Surname cannot be empty")
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull(message = "CPF is required")
    @Pattern(regexp = "\\d{11}", message = "CPF must contain exactly 11 digits")
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    // Campos opcionais
    @Column(name = "telephone", length = 15)
    private String telephone;

    @Column(name = "cep", length = 8)
    private String cep;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "neighborhood", length = 100)
    private String neighborhood;

    @Column(name = "street", length = 200)
    private String street;

    @Column(name = "number", length = 10)
    private String number;

    // Campos com valores padrão
    @Column(name = "isAdmin", nullable = false)
    private boolean isAdmin = false;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender", length = 20)
    private String gender; // "MALE", "FEMALE", "OTHER", "PREFER_NOT_TO_SAY"

    @Column(name = "phone_verified", nullable = false)
    private boolean phoneVerified = false;

    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified = false;

    @NotNull(message = "Status is required")
    @Column(name = "status", nullable = false, length = 20)
    private String status = "ACTIVE"; // "ACTIVE", "INACTIVE", "SUSPENDED"

    @CreationTimestamp
    @Column(name = "creation_timestamp", nullable = false, updatable = false)
    private Instant creationTimestamp;

    @UpdateTimestamp
    @Column(name = "update_timestamp")
    private Instant updateTimestamp;

    // Construtores
    public User() {}

    public User(UUID id, String username, String surname, String email, String telephone,
                String password, String cpf, String cep, String state, String city,
                String neighborhood, String street, String number, boolean isAdmin,
                LocalDate birthDate, String gender, boolean phoneVerified,
                boolean emailVerified, String status, Instant creationTimestamp,
                Instant updateTimestamp) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.cpf = cpf;
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.isAdmin = isAdmin;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneVerified = phoneVerified;
        this.emailVerified = emailVerified;
        this.status = status;
        this.creationTimestamp = creationTimestamp;
        this.updateTimestamp = updateTimestamp;
    }

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getNeighborhood() { return neighborhood; }
    public void setNeighborhood(String neighborhood) { this.neighborhood = neighborhood; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public boolean isPhoneVerified() { return phoneVerified; }
    public void setPhoneVerified(boolean phoneVerified) { this.phoneVerified = phoneVerified; }

    public boolean isEmailVerified() { return emailVerified; }
    public void setEmailVerified(boolean emailVerified) { this.emailVerified = emailVerified; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Instant getCreationTimestamp() { return creationTimestamp; }
    public void setCreationTimestamp(Instant creationTimestamp) { this.creationTimestamp = creationTimestamp; }

    public Instant getUpdateTimestamp() { return updateTimestamp; }
    public void setUpdateTimestamp(Instant updateTimestamp) { this.updateTimestamp = updateTimestamp; }
}