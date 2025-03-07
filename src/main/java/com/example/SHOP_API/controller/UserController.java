package com.example.SHOP_API.controller;


import com.example.SHOP_API.controller.Dto.CreateUserDto;
import com.example.SHOP_API.entity.User;
import com.example.SHOP_API.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){ this.userService = userService; }

@PostMapping()
public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {

    var userId = userService.createUser(createUserDto);

    return ResponseEntity.created(URI.create("/user/" + userId.toString())).build();
}

}
