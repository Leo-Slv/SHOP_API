package com.example.SHOP_API.controller.dto.response;

import java.time.Instant;
import java.util.UUID;

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
        Instant creationTimestamp,
        Instant updateTimestamp
) {
    // Método para converter de Entity para DTO
    public static UserResponseDto fromEntity(com.example.SHOP_API.entity.User user) {
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
                user.getCreationTimestamp(),
                user.getUpdateTimestamp()
        );
    }
}
