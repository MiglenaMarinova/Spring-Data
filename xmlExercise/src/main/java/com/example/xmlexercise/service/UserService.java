package com.example.xmlexercise.service;

import com.example.xmlexercise.model.dto.UserSeedDto;
import com.example.xmlexercise.model.dto.UserWithSoldProductsDto;

import com.example.xmlexercise.model.dto.UsersProductsView4Dto;
import com.example.xmlexercise.model.dto.UsersViewWithSoldProductsDto;
import com.example.xmlexercise.model.entity.User;

import java.util.List;

public interface UserService {
    long getEntityCount();

    void seedUsers(List<UserSeedDto> users);

    User getRandomUser();

    UsersViewWithSoldProductsDto findAllUsersWithSoldProducts();


    UsersProductsView4Dto getAllUsersWithAtLeastOneSoldProduct();
}
