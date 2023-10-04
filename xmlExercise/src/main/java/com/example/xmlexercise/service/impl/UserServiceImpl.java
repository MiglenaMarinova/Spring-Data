package com.example.xmlexercise.service.impl;

import com.example.xmlexercise.model.dto.*;
import com.example.xmlexercise.model.entity.User;
import com.example.xmlexercise.repository.ProductRepository;
import com.example.xmlexercise.repository.UserRepository;
import com.example.xmlexercise.service.UserService;
import com.example.xmlexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;

    }

    @Override
    public long getEntityCount() {
        return userRepository.count();
    }

    @Override
    public void seedUsers(List<UserSeedDto> users) {

        users
                .stream()
                .filter(validationUtil::isValid)
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public User getRandomUser() {
        long randomId = ThreadLocalRandom.current()
                .nextLong(1, userRepository.count()) + 1;

        return userRepository
                .findById(randomId)
                .orElse(null);
    }


    @Override
    public UsersViewWithSoldProductsDto findAllUsersWithSoldProducts() {

        UsersViewWithSoldProductsDto usersViewWithSoldProductsDto = new UsersViewWithSoldProductsDto();
        List<UserWithSoldProductsDto> userWithSoldProductsDtos = userRepository.findAllWithSoldProducts()
                .stream()
                .map(user -> {
                    UserWithSoldProductsDto userWithSoldProductsDto =
                            modelMapper.map(user, UserWithSoldProductsDto.class);
                    Set<SoldProductWithBuyerDto> sold = userWithSoldProductsDto.getProducts()
                            .stream()
                            .filter(p -> p.getBuyerLastName() != null).collect(Collectors.toSet());
                    userWithSoldProductsDto.setProducts(sold);
                    return userWithSoldProductsDto;
                }).collect(Collectors.toList());
        usersViewWithSoldProductsDto.setUsers(userWithSoldProductsDtos);

        return usersViewWithSoldProductsDto;

    }

    @Override
    public UsersProductsView4Dto getAllUsersWithAtLeastOneSoldProduct() {
        List<User> users = userRepository.findAllWithAtLeastOneSoldProduct();

        List<UsersSoldProductsAge4Dto> usersSoldProductsAge4Dtos = users.stream()
                .map(user -> {
                    UsersSoldProductsAge4Dto usersSoldProductsAge4Dto = modelMapper.map(user, UsersSoldProductsAge4Dto.class);

                    usersSoldProductsAge4Dto.getProducts().setCount(user.getSoldProducts().size());

                    return usersSoldProductsAge4Dto;
                })
                .collect(Collectors.toList());
        UsersProductsView4Dto usersProductsView4Dto = new UsersProductsView4Dto();
        usersProductsView4Dto.setUsers(usersSoldProductsAge4Dtos);
        usersProductsView4Dto.setUsersCount(usersSoldProductsAge4Dtos.size());
        System.out.println();
        return usersProductsView4Dto;
    }


}
