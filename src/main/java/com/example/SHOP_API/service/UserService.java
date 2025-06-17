package com.example.SHOP_API.service;

import com.example.SHOP_API.controller.dto.CreateUserDto;
import com.example.SHOP_API.controller.dto.UpdateUserDto;
import com.example.SHOP_API.entity.User;
import com.example.SHOP_API.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto){

        LocalDate birthDate = LocalDate.parse(createUserDto.birthDate());
        String statusActivated= "ACTIVE";

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
                false,
                birthDate,
                createUserDto.gender(),
                false,
                false,
                statusActivated,
                Instant.now(),
                null);

        var UserSaved = userRepository.save(Entity);

        return UserSaved.getId();
    }

    public Optional <User> getUserById (String id){

        return userRepository.findById(UUID.fromString(id));
    }

    public List<User> listUsers(){ return userRepository.findAll();}

    public void updateUserById (String id,
                                UpdateUserDto updateUserDto){

        var userId = UUID.fromString(id);

        var userEntity = userRepository.findById(userId);

        if (userEntity.isPresent()){

            var user = userEntity.get();


            if ( updateUserDto.username() !=null ) {
                user.setUsername(updateUserDto.username());
            }

            if ( updateUserDto.surname() !=null ) {
                user.setSurname((updateUserDto.surname()));
            }

            if ( updateUserDto.password() != null ) {
                user.setPassword(updateUserDto.password());
            }

            if ( updateUserDto.cep() != null ) {
                user.setCep(updateUserDto.cep());
            }

            if ( updateUserDto.state() != null ){
                user.setState(updateUserDto.state());
            }

            if ( updateUserDto.city() != null ){
                user.setCity(updateUserDto.city());
            }

            if ( updateUserDto.neighborhood() != null ){
                user.setNeighborhood(updateUserDto.neighborhood());
            }

            if ( updateUserDto.street() != null ){
                user.setStreet(updateUserDto.street());
            }

            if ( updateUserDto.number() != null ){
                user.setNumber(updateUserDto.number());
            }

            if (updateUserDto.birthDate() != null && !updateUserDto.birthDate().trim().isEmpty()) {
                try {
                    LocalDate birthDate = LocalDate.parse(updateUserDto.birthDate().trim());

                    // Validações de negócio
                    if (birthDate.isAfter(LocalDate.now())) {
                        throw new IllegalArgumentException("Data de nascimento não pode ser no futuro");
                    }

                    if (birthDate.isBefore(LocalDate.now().minusYears(120))) {
                        throw new IllegalArgumentException("Data de nascimento não pode ser há mais de 120 anos");
                    }

                    user.setBirthDate(birthDate);

                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Formato de data inválido. Use yyyy-MM-dd (ex: 1990-05-15)");
                }
            }

            if ( updateUserDto.gender() != null){
                user.setGender(updateUserDto.gender());
            }

            userRepository.save(user);

        }
    }

    public void deleteUserById (String id){

        var userId = UUID.fromString(id);

        var userExists = userRepository.existsById(userId);

        if ( userExists ){
            userRepository.deleteById(userId);
        }
    }
}
