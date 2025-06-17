package com.example.SHOP_API.service;

import com.example.SHOP_API.controller.dto.CreateUserDto;
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
import java.time.LocalDate;
import java.util.List;
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
            LocalDate birthDate = LocalDate.of(1990, 5, 15);

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
                    false,
                    birthDate,
                    "MALE",
                    false,
                    false,
                    "ACTIVE",
                    Instant.now(),
                    null
            );

            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());


            var input = new CreateUserDto(
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
                    "1990-05-15",
                    "MALE"

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
        LocalDate birthDate = LocalDate.of(1990, 5, 15);

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
                false,
                birthDate,
                "MALE",
                false,
                false,
                "ACTIVE",
                Instant.now(),
                null
        );

        doThrow(new RuntimeException()).when(userRepository).save(any());

        var input = new CreateUserDto(
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
                "1990-05-15",
                "MALE"
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
        LocalDate birthDate = LocalDate.of(1990, 5, 15);

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
                false,
                birthDate,
                "MALE",
                false,
                false,
                "ACTIVE",
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

    @Nested
    class listUsers{


        @Test
        @DisplayName("Should return all users with success")
        void shouldReturnAllUsersWithSuccess(){

            //Arrange
            LocalDate birthDate = LocalDate.of(1990, 5, 15);

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
                    false,
                    birthDate,
                    "MALE",
                    false,
                    false,
                    "ACTIVE",
                    Instant.now(),
                    null
            );

            var userList = List.of(user);

            doReturn(List.of(user))
                    .when(userRepository)
                    .findAll();

            //Act
            var output = userService.listUsers();

            //Assert
            assertNotNull(output);
            assertEquals(userList.size(), output.size());
        }
    }

    @Nested
    class deleteById{

        @Test
        @DisplayName("should delete user with success when user exists")
        void shouldDeleteUserWithSuccessWhenUserExists(){

            //Arrange
            doReturn(true)
                    .when(userRepository)
                    .existsById(uuidArgumentCaptor.capture());
        }
    }
}
