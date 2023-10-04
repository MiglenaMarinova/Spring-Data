package com.example.automappingdto.services;

import com.example.automappingdto.models.dto.LoginUserDto;
import com.example.automappingdto.models.dto.OwnedGameDto;
import com.example.automappingdto.models.dto.UserRegisterDto;
import com.example.automappingdto.models.entities.User;

import java.util.List;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(LoginUserDto loginUserDto);

    void logout();

    List<OwnedGameDto> getOwnedGames();


}
