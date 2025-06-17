package com.example.SHOP_API.service;

import com.example.SHOP_API.controller.dto.CreateUserDto;
import com.example.SHOP_API.controller.dto.UpdateUserDto;
import com.example.SHOP_API.controller.dto.response.UserResponseDto;
import com.example.SHOP_API.entity.User;
import com.example.SHOP_API.exception.UserNotFoundException;
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
                    LocalDate.of(1990, 5, 15),
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
                    "email@mail.com",
                    "123456789",
                    "password",
                    "987654321",
                    "246810",
                    "state",
                    "city",
                    "neighborhood",
                    "street",
                    "number",
                    LocalDate.of(1990, 5, 15),
                    "MALE"
            );

            //Act
            var output = userService.createUser(input);

            //Assert
            assertNotNull(output);
            assertInstanceOf(UserResponseDto.class, output);

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
            assertEquals(input.street(), userCaptured.getStreet());
            assertEquals(input.number(), userCaptured.getNumber());
            assertEquals(input.birthDate(), userCaptured.getBirthDate());
            assertEquals(input.gender(), userCaptured.getGender());
        }

        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenErrorOccurs() {

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
                    "number",
                    LocalDate.of(1990, 5, 15),
                    "MALE"
            );

            //Act & Assert
            assertThrows(RuntimeException.class, () -> userService.createUser(input));
        }
    }

    @Nested
    class getUserById {

        @DisplayName("Should get user by id with success when user exists")
        @Test
        void shouldGetUserByIdWithSuccessWhenUserExists() {

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
                    false,
                    LocalDate.of(1990, 5, 15),
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
            assertNotNull(output);
            assertInstanceOf(UserResponseDto.class, output);
            assertEquals(user.getId(), uuidArgumentCaptor.getValue());
            assertEquals(user.getUsername(), output.username());
            assertEquals(user.getEmail(), output.email());
        }

        @DisplayName("Should throw UserNotFoundException when user does not exist")
        @Test
        void shouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {

            //Arrange
            var userId = UUID.randomUUID();

            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(uuidArgumentCaptor.capture());

            //Act & Assert
            assertThrows(UserNotFoundException.class,
                    () -> userService.getUserById(userId.toString()));
            assertEquals(userId, uuidArgumentCaptor.getValue());
        }
    }

    @Nested
    class listUsers {

        @Test
        @DisplayName("Should return all users with success")
        void shouldReturnAllUsersWithSuccess() {

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
                    false,
                    LocalDate.of(1990, 5, 15),
                    "MALE",
                    false,
                    false,
                    "ACTIVE",
                    Instant.now(),
                    null
            );

            var userList = List.of(user);

            doReturn(userList)
                    .when(userRepository)
                    .findAll();

            //Act
            var output = userService.listUsers();

            //Assert
            assertNotNull(output);
            assertEquals(userList.size(), output.size());
            assertInstanceOf(List.class, output);
            // Verifica se todos os elementos são UserResponseDto
            assertTrue(output.stream().allMatch(dto -> dto instanceof UserResponseDto));
        }
    }

    @Nested
    class updateUserById {

        @Test
        @DisplayName("Should update user with success when user exists")
        void shouldUpdateUserWithSuccessWhenUserExists() {

            //Arrange
            var userId = UUID.randomUUID();
            var existingUser = new User(
                    userId,
                    "oldName",
                    "oldSurname",
                    "old@mail.com",
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
                    LocalDate.of(1990, 5, 15),
                    "MALE",
                    false,
                    false,
                    "ACTIVE",
                    Instant.now(),
                    null
            );

            var updateDto = new UpdateUserDto(
                    "newName",
                    "newSurname",
                    null, // password não alterado
                    null, // cep não alterado
                    null, // state não alterado
                    null, // city não alterado
                    null, // neighborhood não alterado
                    null, // street não alterado
                    null, // number não alterado
                    LocalDate.of(1985, 10, 20),
                    "FEMALE"
            );

            doReturn(Optional.of(existingUser))
                    .when(userRepository)
                    .findById(userId);

            doReturn(existingUser)
                    .when(userRepository)
                    .save(userArgumentCaptor.capture());

            //Act
            var output = userService.updateUserById(userId.toString(), updateDto);

            //Assert
            assertNotNull(output);
            assertInstanceOf(UserResponseDto.class, output);

            var savedUser = userArgumentCaptor.getValue();
            assertEquals("newName", savedUser.getUsername());
            assertEquals("newSurname", savedUser.getSurname());
            assertEquals(LocalDate.of(1985, 10, 20), savedUser.getBirthDate());
            assertEquals("FEMALE", savedUser.getGender());
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when user does not exist")
        void shouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {

            //Arrange
            var userId = UUID.randomUUID();
            var updateDto = new UpdateUserDto(
                    "newName", null, null, null, null, null, null, null, null, null, null
            );

            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(userId);

            //Act & Assert
            assertThrows(UserNotFoundException.class,
                    () -> userService.updateUserById(userId.toString(), updateDto));
        }
    }

    @Nested
    class deleteById {

        @Test
        @DisplayName("Should delete user with success when user exists")
        void shouldDeleteUserWithSuccessWhenUserExists() {

            //Arrange
            var userId = UUID.randomUUID();

            doReturn(true)
                    .when(userRepository)
                    .existsById(uuidArgumentCaptor.capture());

            doNothing()
                    .when(userRepository)
                    .deleteById(userId);

            //Act
            userService.deleteUserById(userId.toString());

            //Assert
            assertEquals(userId, uuidArgumentCaptor.getValue());
            verify(userRepository, times(1)).deleteById(userId);
        }

        @Test
        @DisplayName("Should throw UserNotFoundException when user does not exist")
        void shouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {

            //Arrange
            var userId = UUID.randomUUID();

            doReturn(false)
                    .when(userRepository)
                    .existsById(userId);

            //Act & Assert
            assertThrows(UserNotFoundException.class,
                    () -> userService.deleteUserById(userId.toString()));
        }
    }
}