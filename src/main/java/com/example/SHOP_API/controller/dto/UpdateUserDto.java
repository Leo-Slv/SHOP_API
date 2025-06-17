package com.example.SHOP_API.controller.dto;

import java.time.LocalDate;

public record UpdateUserDto(
        String username,
        String surname,
        String password,
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
