package com.example.SHOP_API.controller.Dto;

public record CreateUserDto( String username, String surname, String email, String telephone, String password, String cpf, String cep, String state, String city, String neighborhood, String street, String number ) {
}
