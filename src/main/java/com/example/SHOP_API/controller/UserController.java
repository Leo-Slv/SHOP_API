package com.example.SHOP_API.controller;

import com.example.SHOP_API.controller.dto.CreateUserDto;
import com.example.SHOP_API.controller.dto.UpdateUserDto;
import com.example.SHOP_API.controller.dto.response.ApiResponse;
import com.example.SHOP_API.controller.dto.response.UserResponseDto;
import com.example.SHOP_API.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        UserResponseDto userResponse = userService.createUser(createUserDto);
        ApiResponse<UserResponseDto> response = ApiResponse.success(userResponse, "User created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(@PathVariable String id) {
        UserResponseDto user = userService.getUserById(id);
        ApiResponse<UserResponseDto> response = ApiResponse.success(user, "User retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> listUsers() {
        List<UserResponseDto> users = userService.listUsers();
        String message = users.isEmpty() ? "No users found" : "Users retrieved successfully";
        ApiResponse<List<UserResponseDto>> response = ApiResponse.success(users, message);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateUserById(
            @PathVariable("id") String id,
            @RequestBody UpdateUserDto updateUserDto) {

        UserResponseDto updatedUser = userService.updateUserById(id, updateUserDto);
        ApiResponse<UserResponseDto> response = ApiResponse.success(updatedUser, "User updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUserById(@PathVariable("id") String id) {
        userService.deleteUserById(id);
        ApiResponse<Void> response = ApiResponse.success("User deleted successfully");
        return ResponseEntity.ok(response);
    }
}