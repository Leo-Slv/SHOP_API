package com.example.SHOP_API.service;

import com.example.SHOP_API.controller.Dto.CreateUserDto;
import com.example.SHOP_API.entity.User;
import com.example.SHOP_API.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Nested
    class createUser {

        @Test
        @DisplayName("Should create a user with success")
        void shouldCreateAUserWithSuccess() {

            //Arrange
            var user = new User(
                    UUID.randomUUID(),
                    "name",
                    "surname",
                    "mail@mail.com",
                    "123456789",
                    "password",
                    "987654321",
                    "246810",
                    "state",
                    "city",
                    "neighborhood",
                    "street",
                    "number",
                    Instant.now(),
                    null
            );

            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());

            var input = new CreateUserDto(
                    "name",
                    "surname",
                    "email@mail.com",
                    "123456789",
                    "password",
                    "987654321",
                    "246810",
                    "state",
                    "city",
                    "neighborhood",
                    "street",
                    "number"
                    );

            //Act
            var output = userService.createUser(input);

            //Assert
            assertNotNull(output);

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(input.username(), userCaptured.getUsername());
            assertEquals(input.surname(), userCaptured.getSurname());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.telephone(), userCaptured.getTelephone());
            assertEquals(input.password(), userCaptured.getPassword());
            assertEquals(input.cpf(), userCaptured.getCpf());
            assertEquals(input.cep(), userCaptured.getCep());
            assertEquals(input.state(), userCaptured.getState());
            assertEquals(input.city(), userCaptured.getCity());
            assertEquals(input.neighborhood(), userCaptured.getNeighborhood());

        }
    }

    @Test
    @DisplayName("Should throw exception when error occurs")
    void shouldThrowExceptionWhenErrorOccurs (){

        //Arrange
        var user = new User(
                UUID.randomUUID(),
                "name",
                "surname",
                "mail@mail.com",
                "123456789",
                "password",
                "987654321",
                "246810",
                "state",
                "city",
                "neighborhood",
                "street",
                "number",
                Instant.now(),
                null
        );

        doThrow(new RuntimeException()).when(userRepository).save(any());

        var input = new CreateUserDto(
                "name",
                "surname",
                "email@mail.com",
                "123456789",
                "password",
                "987654321",
                "246810",
                "state",
                "city",
                "neighborhood",
                "street",
                "number"
        );

        //Act & Assert
        assertThrows(RuntimeException.class, () -> userService.createUser(input));
    }

    @Nested
    class getUserById{

    @DisplayName("Should get user by id with success when optional is present")
    @Test
    void shouldGetUserByIdWithSuccessWhenOptionalIsPresent(){

        //Arrange
        var user = new User(
                UUID.randomUUID(),
                "name",
                "surname",
                "mail@mail.com",
                "123456789",
                "password",
                "987654321",
                "246810",
                "state",
                "city",
                "neighborhood",
                "street",
                "number",
                Instant.now(),
                null
        );

        doReturn(Optional.of(user))
                .when(userRepository)
                .findById(uuidArgumentCaptor.capture());

        //Act
        var output = userService.getUserById(user.getId().toString());

        //Assert
        assertTrue(output.isPresent());
        assertEquals(user.getId(), uuidArgumentCaptor.getValue());
        }

        @DisplayName("Should get user by id with success when optional is empty")
        @Test
        void shouldGetUserByIdWithSuccessWhenOptionalIsEmpty(){

            //Arrange
            var userId = UUID.randomUUID();

            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(uuidArgumentCaptor.capture());

            //Act
            var output = userService.getUserById(userId.toString());

            //Assert
            assertTrue(output.isEmpty());
            assertEquals(userId, uuidArgumentCaptor.getValue());
        }
    }
}
