package com.example.automappingdto.services.impl;

import com.example.automappingdto.models.dto.LoginUserDto;
import com.example.automappingdto.models.dto.OwnedGameDto;
import com.example.automappingdto.models.dto.UserRegisterDto;
import com.example.automappingdto.models.entities.Game;
import com.example.automappingdto.models.entities.User;
import com.example.automappingdto.repositories.GameRepository;
import com.example.automappingdto.repositories.UserRepository;
import com.example.automappingdto.services.UserService;
import com.example.automappingdto.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final GameRepository gameRepository;

    private User loggedUser;


    public UserServiceImpl(UserRepository userRepository, ValidationUtil validationUtil,
                           ModelMapper modelMapper, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
//        this.loggedUser = getLoggedUser();
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            System.out.println("Wrong confirm password");
            return;
        }

        Set<ConstraintViolation<UserRegisterDto>> violations = validationUtil.violations(userRegisterDto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }
        User user = modelMapper.map(userRegisterDto, User.class);
        userRepository.save(user);
        System.out.printf("%s was registered%n", userRegisterDto.getFullName());

    }

    @Override
    public void loginUser(LoginUserDto loginUserDto) {
        Set<ConstraintViolation<LoginUserDto>> violations = validationUtil.violations(loginUserDto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }
        User user = userRepository
                .findUserByEmailAndPassword(loginUserDto.getEmail(), loginUserDto.getPassword())
                .orElse(null);
        if (user == null) {
            System.out.println("Incorrect username / password");
        } else {
            loggedUser = user;
            System.out.printf("Successfully logged in %s%n", loggedUser.getFullName());
        }
    }

    @Override
    public void logout() {
        if (loggedUser == null) {
            System.out.println("Cannot log out. No user was logged in.");
            return;
        }
        System.out.printf("User %s successfully logged out%n", loggedUser.getFullName());
        loggedUser = null;
    }

    @Override
    public List<OwnedGameDto> getOwnedGames() {
        if (loggedUser == null){
            System.out.println("Please log in");
        }

        List<Game> loggedUserGames = new ArrayList<>();;
        Game purchasedGame = gameRepository.findGamesByTitle("Overwatch");
        if (loggedUser != null){
            if (purchasedGame != null) {
                loggedUserGames.add(purchasedGame);
            }

        }
        List<OwnedGameDto> ownedGameDtoList = loggedUserGames
                .stream().map(game -> modelMapper.map(purchasedGame, OwnedGameDto.class))
                .collect(Collectors.toList());

        return ownedGameDtoList;
    }


    public User getLoggedUser() {
        return loggedUser;
    }


}


