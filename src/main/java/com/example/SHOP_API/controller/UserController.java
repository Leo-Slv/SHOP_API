package com.example.SHOP_API.controller;


import com.example.SHOP_API.controller.Dto.CreateUserDto;
import com.example.SHOP_API.controller.Dto.UpdateUserDto;
import com.example.SHOP_API.entity.User;
import com.example.SHOP_API.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){ this.userService = userService; }

@PostMapping()
public ResponseEntity<User> createUser (@RequestBody CreateUserDto createUserDto) {

    var userId = userService.createUser(createUserDto);

    return ResponseEntity.created(URI.create("/user/" + userId.toString())).build();
}

@GetMapping("/{id}")
public ResponseEntity<User> getUserById (@PathVariable String id){

        var user = userService.getUserById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

@GetMapping
    public ResponseEntity<List<User>> listUsers(){

        var users = userService.listUsers();

        return ResponseEntity.ok(users);
}

@PutMapping("/{id}")

    public ResponseEntity<Void> updateUserById (@PathVariable("id") String id,
                                                @RequestBody UpdateUserDto updateUserDto){

        userService.updateUserById(id , updateUserDto);

        return ResponseEntity.noContent().build();
    }

@DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteUserById (@PathVariable("id") String id) {

        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();
    }
}
