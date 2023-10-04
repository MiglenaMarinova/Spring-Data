package com.example.automappingdto;

import com.example.automappingdto.models.dto.GameAddDto;
import com.example.automappingdto.models.dto.LoginUserDto;
import com.example.automappingdto.models.dto.UserRegisterDto;
import com.example.automappingdto.services.GameService;
import com.example.automappingdto.services.UserService;
import com.example.automappingdto.services.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final UserService userService;
    private final GameService gameService;
    private final UserServiceImpl userServiceImp;

    public CommandLineRunnerImpl(UserService userService, GameService gameService, UserServiceImpl userServiceImp) {
        this.userService = userService;
        this.gameService = gameService;
        this.userServiceImp = userServiceImp;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("Enter your command:");
            String[] input = bufferedReader.readLine().split("\\|");
            String command = input[0];
            switch (command) {
                case "RegisterUser" -> userService
                        .registerUser(new UserRegisterDto(input[1], input[2], input[3], input[4]));


                case "LoginUser" -> userService
                        .loginUser(new LoginUserDto(input[1], input[2]));
                case "Logout" -> userService
                        .logout();
                case "AddGame" -> gameService
                        .addGame(new GameAddDto(input[1],
                                new BigDecimal(input[2]),
                                Double.parseDouble(input[3]),
                                input[4],
                                input[5],
                                input[6],
                                input[7]));
                case "EditGame" -> gameService
                        .editGame(Long.parseLong(input[1]),
                                new BigDecimal(input[2].split("=")[1]),
                                Double.parseDouble(input[3].split("=")[1]));
                case "DeleteGame" -> gameService.deleteGame(Long.parseLong(input[1]));
                case "AllGames" -> printAllGames();
                case "DetailGame" -> printDetailGame(input[1]);
                case "OwnedGames" -> printOwnedGames();
            }
        }

    }

    private void printOwnedGames() {
        userService.getOwnedGames().forEach(System.out::println);
    }

    private void printDetailGame(String title) {
        System.out.println(gameService.findGameByTitle(title));
    }

    private void printAllGames() {
        gameService.findAllGames().forEach(System.out::println);
    }
}
