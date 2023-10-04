package com.example.jsonexercise.servicies.impl;

import com.example.jsonexercise.models.dtos.*;
import com.example.jsonexercise.models.entities.User;
import com.example.jsonexercise.repositories.ProductRepository;
import com.example.jsonexercise.repositories.UserRepository;
import com.example.jsonexercise.servicies.UserService;
import com.example.jsonexercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.example.jsonexercise.constant.GlobalConstant.RESOURCES_FILE_PATH;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_FILE_NAME = "users.json";
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;


    public UserServiceImpl(ModelMapper modelMapper, Gson gson, UserRepository userRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;

    }

    @Override
    public void seedUsers() throws IOException {

        if (userRepository.count() == 0) {
            Arrays.stream(gson.fromJson(
                            Files.readString(Path.of(RESOURCES_FILE_PATH + USER_FILE_NAME)),
                            UserSeedDto[].class))
                    .filter(validationUtil::isValid)
                    .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                    .forEach(userRepository::save);

        }

    }

    @Override
    public User findRandomUser() {
        long randomId = ThreadLocalRandom
                .current().nextLong(1, userRepository.count()+1);
        return userRepository
                .findById(randomId)
                .orElse(null);
    }

    @Override
    public List<UserSoldDto> findUserWithAtLestOneSoldProduct() {
        return userRepository.findAllUsersWithSoldProductWithBuyerOrderByLastNameThenByFirstName()
                .stream()
                .map(user -> {
                    UserSoldDto userSoldDto = modelMapper.map(user, UserSoldDto.class);
                    Set<ProductWithBuyerDto> sold = userSoldDto.getSoldProducts()
                            .stream().filter(p -> p.getBuyerLastName() != null)
                            .collect(Collectors.toSet());
                    userSoldDto.setSoldProducts(sold);
                    return userSoldDto;
                })
                .collect(Collectors.toList());


    }

    @Override
    public UsersAndProductsDto usersAndProducts() {
        List<User> users = userRepository.usersAndProducts().orElseThrow(NoSuchElementException::new);

        List<UserNameAgeSoldProductsDto> userSoldProduct =
                users.stream()
                        .map(u -> {
                            UserNameAgeSoldProductsDto user = modelMapper.map(u, UserNameAgeSoldProductsDto.class);
                            user.getProducts().setCount(u.getSoldProducts().size());
                            return user;
                        })
                        .collect(Collectors.toList());
        UsersAndProductsDto usersAndProductsDto = new UsersAndProductsDto(userSoldProduct);
        return usersAndProductsDto;
    }
}
