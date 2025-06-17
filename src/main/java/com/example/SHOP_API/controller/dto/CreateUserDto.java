package com.example.SHOP_API.controller.dto;

import java.time.LocalDate;

public record CreateUserDto(
        String username,
        String surname,
        String email,
        String telephone,
        String password,
        String cpf,
        String cep,
        String state,
        String city,
        String neighborhood,
        String street,
        String number,
        LocalDate birthDate,
        String gender
) {
}
