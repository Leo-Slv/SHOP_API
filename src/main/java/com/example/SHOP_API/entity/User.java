package com.example.SHOP_API.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table( name = "users")
public class User {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID id;

    @Column( name = "username" )
    private String username;

    @Column( name = "surname" )
    private String surname;

    @Column( name = "email" )
    private String email;

    @Column( name = "telephone" )
    private String telephone;

    @Column( name = "password" )
    private String password;

    @Column( name = "cpf" )
    private String cpf;

    @Column( name = "cep" )
    private String cep;

    @Column( name = "state" )
    private String state;

    @Column( name = "city" )
    private String city;

    @Column( name = "neighborhood" )
    private String neighborhood;

    @Column( name = "street")
    private String street;

    @Column( name = "number")
    private String number;

    @Column ( name = "isAdmin")
    private boolean isAdmin;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender", length = 20)
    private String gender; // "MALE", "FEMALE", "OTHER", "PREFER_NOT_TO_SAY"

    @Column ( name = "phone_verified")
    private boolean phone_verified;

    @Column ( name = "email_verified")
    private boolean email_verified;

    @Column ( name = "status")
    private String status;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    public User() {}

    public User( UUID id, String username, String surname, String email, String telephone, String password, String cpf, String cep, String state, String city, String neighborhood, String street, String number, boolean isAdmin, LocalDate birthDate, String gender, boolean phone_verified, boolean email_verified,  String status, Instant creationTimestamp, Instant updateTimestamp ){

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
        this.phone_verified = phone_verified;
        this.email_verified = email_verified;
        this.status = status;
        this.creationTimestamp = creationTimestamp;
        this.updateTimestamp = updateTimestamp;


    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isPhone_verified() {
        return phone_verified;
    }

    public void setPhone_verified(boolean phone_verified) {
        this.phone_verified = phone_verified;
    }

    public boolean isEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Instant getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Instant updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}
