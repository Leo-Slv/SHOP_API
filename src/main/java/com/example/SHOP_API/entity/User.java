package com.example.SHOP_API.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table( name = "users")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    private UUID id ;

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

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    public User( UUID id, String username, String surname, String email, String telephone, String password, String cpf, String cep, String state, String city, String neighborhood, String street, String number, Instant creationTimestamp, Instant updateTimestamp ){

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
