package com.example.SHOP_API.controller.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record CreateUserDto(
        @NotNull(message = "Username is required")
        @NotBlank(message = "Username cannot be empty")
        @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
        String username,

        @NotNull(message = "Surname is required")
        @NotBlank(message = "Surname cannot be empty")
        @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
        String surname,

        @NotNull(message = "Email is required")
        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email should be valid")
        @Size(max = 100, message = "Email must not exceed 100 characters")
        String email,

        @Size(max = 15, message = "Telephone must not exceed 15 characters")
        @Pattern(regexp = "^[\\d\\s\\(\\)\\+\\-]*$", message = "Telephone contains invalid characters")
        String telephone,

        @NotNull(message = "Password is required")
        @NotBlank(message = "Password cannot be empty")
        @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
        String password,

        @NotNull(message = "CPF is required")
        @NotBlank(message = "CPF cannot be empty")
        @Pattern(regexp = "\\d{11}", message = "CPF must contain exactly 11 digits")
        String cpf,

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