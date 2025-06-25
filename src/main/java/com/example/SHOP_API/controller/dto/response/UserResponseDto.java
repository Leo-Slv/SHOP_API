package com.example.SHOP_API.controller.dto.response;

import com.example.SHOP_API.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponseDto(
        UUID id,
        String username,
        String surname,
        String email,
        String telephone,
        String cpf,
        String cep,
        String state,
        String city,
        String neighborhood,
        String street,
        String number,
        boolean isAdmin,
        LocalDate birthDate,
        String gender,
        boolean phoneVerified,
        boolean emailVerified,
        String status,
        Instant creationTimestamp,
        Instant updateTimestamp
) {
    // Método para converter de Entity para DTO (NUNCA inclui password por segurança)
    public static UserResponseDto fromEntity(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getSurname(),
                user.getEmail(),
                user.getTelephone(),
                user.getCpf(),
                user.getCep(),
                user.getState(),
                user.getCity(),
                user.getNeighborhood(),
                user.getStreet(),
                user.getNumber(),
                user.isAdmin(),
                user.getBirthDate(),
                user.getGender(),
                user.isPhoneVerified(),
                user.isEmailVerified(),
                user.getStatus(),
                user.getCreationTimestamp(),
                user.getUpdateTimestamp()
        );
    }

    // Versão sem dados sensíveis para listagem pública
    public static UserResponseDto fromEntityPublic( User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getSurname(),
                user.getEmail(),
                null, // telephone oculto
                null, // cpf oculto
                null, // cep oculto
                user.getState(),
                user.getCity(),
                null, // neighborhood oculto
                null, // street oculto
                null, // number oculto
                user.isAdmin(),
                null, // birthDate oculto
                null, // gender oculto
                user.isPhoneVerified(),
                user.isEmailVerified(),
                user.getStatus(),
                user.getCreationTimestamp(),
                user.getUpdateTimestamp()
        );
    }
}
