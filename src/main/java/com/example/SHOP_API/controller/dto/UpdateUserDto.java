package com.example.SHOP_API.controller.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record UpdateUserDto(
        @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
        String username,

        @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
        String surname,

        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        String password,

        @Pattern(regexp = "\\d{8}", message = "CEP must contain exactly 8 digits")
        String cep,

        @Size(max = 50, message = "State must not exceed 50 characters")
        String state,

        @Size(max = 100, message = "City must not exceed 100 characters")
        String city,

        @Size(max = 100, message = "Neighborhood must not exceed 100 characters")
        String neighborhood,

        @Size(max = 200, message = "Street must not exceed 200 characters")
        String street,

        @Size(max = 10, message = "Number must not exceed 10 characters")
        String number,

        @Past(message = "Birth date must be in the past")
        LocalDate birthDate,

        @Pattern(regexp = "^(MALE|FEMALE|OTHER|PREFER_NOT_TO_SAY)$",
                message = "Gender must be one of: MALE, FEMALE, OTHER, PREFER_NOT_TO_SAY")
        String gender
) {}