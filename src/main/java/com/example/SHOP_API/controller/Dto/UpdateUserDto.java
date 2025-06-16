package com.example.SHOP_API.controller.Dto;

import java.util.Date;

public record UpdateUserDto(String username, String surname, String password, String cep, String state, String city, String neighborhood, String street, String number, String birthDate, String gender ) {
}
