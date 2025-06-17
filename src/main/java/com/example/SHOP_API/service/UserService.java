package com.example.SHOP_API.service;

import com.example.SHOP_API.controller.dto.CreateUserDto;
import com.example.SHOP_API.controller.dto.UpdateUserDto;
import com.example.SHOP_API.controller.dto.response.UserResponseDto;
import com.example.SHOP_API.entity.User;
import com.example.SHOP_API.exception.UserNotFoundException;
import com.example.SHOP_API.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(CreateUserDto createUserDto) {
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.surname(),
                createUserDto.email(),
                createUserDto.telephone(),
                createUserDto.password(),
                createUserDto.cpf(),
                createUserDto.cep(),
                createUserDto.state(),
                createUserDto.city(),
                createUserDto.neighborhood(),
                createUserDto.street(),
                createUserDto.number(),
                false, // isAdmin sempre false para novos usuários
                createUserDto.birthDate(),
                createUserDto.gender(),
                false, // phone_verified inicialmente false
                false, // email_verified inicialmente false
                "ACTIVE", // status padrão como ACTIVE
                Instant.now(),
                null
        );

        var savedUser = userRepository.save(entity);
        return UserResponseDto.fromEntity(savedUser);
    }

    public UserResponseDto getUserById(String id) {
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException(id, "id"));
        return UserResponseDto.fromEntity(user);
    }

    public List<UserResponseDto> listUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::fromEntityPublic) // Usa versão pública para listagem
                .toList();
    }

    public UserResponseDto updateUserById(String id, UpdateUserDto updateUserDto) {
        var userId = UUID.fromString(id);
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(id, "id"));

        // Atualiza apenas campos não nulos do UpdateUserDto
        if (updateUserDto.username() != null) {
            user.setUsername(updateUserDto.username());
        }
        if (updateUserDto.surname() != null) {
            user.setSurname(updateUserDto.surname());
        }
        if (updateUserDto.password() != null) {
            user.setPassword(updateUserDto.password());
        }
        if (updateUserDto.cep() != null) {
            user.setCep(updateUserDto.cep());
        }
        if (updateUserDto.state() != null) {
            user.setState(updateUserDto.state());
        }
        if (updateUserDto.city() != null) {
            user.setCity(updateUserDto.city());
        }
        if (updateUserDto.neighborhood() != null) {
            user.setNeighborhood(updateUserDto.neighborhood());
        }
        if (updateUserDto.street() != null) {
            user.setStreet(updateUserDto.street());
        }
        if (updateUserDto.number() != null) {
            user.setNumber(updateUserDto.number());
        }
        if (updateUserDto.birthDate() != null) {
            user.setBirthDate(updateUserDto.birthDate());
        }
        if (updateUserDto.gender() != null) {
            user.setGender(updateUserDto.gender());
        }

        // Atualiza automaticamente o timestamp de atualização (se usando @UpdateTimestamp)
        var updatedUser = userRepository.save(user);
        return UserResponseDto.fromEntity(updatedUser);
    }

    public void deleteUserById(String id) {
        var userId = UUID.fromString(id);
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(id, "id");
        }
        userRepository.deleteById(userId);
    }
}