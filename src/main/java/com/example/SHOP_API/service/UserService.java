package com.example.SHOP_API.service;

import com.example.SHOP_API.controller.dto.CreateUserDto;
import com.example.SHOP_API.controller.dto.UpdateUserDto;
import com.example.SHOP_API.controller.dto.response.UserResponseDto;
import com.example.SHOP_API.entity.User;
import com.example.SHOP_API.exception.UserNotFoundException;
import com.example.SHOP_API.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // ← NOVO!


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto createUser(CreateUserDto createUserDto) {

        String hashedPassword = passwordEncoder.encode(createUserDto.password());

        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.surname(),
                createUserDto.email(),
                createUserDto.telephone(),
                hashedPassword,
                createUserDto.cpf(),
                createUserDto.cep(),
                createUserDto.state(),
                createUserDto.city(),
                createUserDto.neighborhood(),
                createUserDto.street(),
                createUserDto.number(),
                false,
                createUserDto.birthDate(),
                createUserDto.gender(),
                false,
                false,
                "ACTIVE",
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
                .map(UserResponseDto::fromEntityPublic)
                .toList();
    }

    public UserResponseDto updateUserById(String id, UpdateUserDto updateUserDto) {
        var userId = UUID.fromString(id);
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(id, "id"));

        // Campos normais...
        if (updateUserDto.username() != null) {
            user.setUsername(updateUserDto.username());
        }
        if (updateUserDto.surname() != null) {
            user.setSurname(updateUserDto.surname());
        }

        if (updateUserDto.password() != null) {
            String hashedPassword = passwordEncoder.encode(updateUserDto.password());
            user.setPassword(hashedPassword);
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

    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    public UserResponseDto authenticateUser(String email, String rawPassword) {
        // TODO: Implementar findByEmail no UserRepository
        // User user = userRepository.findByEmail(email)
        //         .orElseThrow(() -> new UserNotFoundException(email, "email"));
        //
        // if (passwordEncoder.matches(rawPassword, user.getPassword())) {
        //     return UserResponseDto.fromEntity(user);
        // } else {
        //     throw new InvalidCredentialsException("Invalid password");
        // }

        // Por enquanto retorna null - implementar quando necessário
        return null;
    }
}