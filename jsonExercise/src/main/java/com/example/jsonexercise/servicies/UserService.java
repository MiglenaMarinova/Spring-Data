package com.example.jsonexercise.servicies;

import com.example.jsonexercise.models.dtos.UserSoldDto;
import com.example.jsonexercise.models.dtos.UsersAndProductsDto;
import com.example.jsonexercise.models.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers() throws IOException;

    User findRandomUser();


    List<UserSoldDto> findUserWithAtLestOneSoldProduct();


    UsersAndProductsDto usersAndProducts();
}
