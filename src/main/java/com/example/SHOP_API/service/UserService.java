package com.example.SHOP_API.service;

import com.example.SHOP_API.controller.Dto.CreateUserDto;
import com.example.SHOP_API.entity.User;
import com.example.SHOP_API.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto){

        var Entity = new User( UUID.randomUUID(),
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
                Instant.now(),
                null);

        var UserSaved = userRepository.save(Entity);

        return UserSaved.getId();
    }
}
