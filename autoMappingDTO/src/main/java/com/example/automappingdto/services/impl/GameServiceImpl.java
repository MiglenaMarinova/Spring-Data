package com.example.automappingdto.services.impl;

import com.example.automappingdto.models.dto.AllGamesDto;
import com.example.automappingdto.models.dto.DetailGameDto;
import com.example.automappingdto.models.dto.GameAddDto;
import com.example.automappingdto.models.entities.Game;
import com.example.automappingdto.repositories.GameRepository;
import com.example.automappingdto.services.GameService;
import com.example.automappingdto.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {
        Set<ConstraintViolation<GameAddDto>> violations = validationUtil.violations(gameAddDto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }
        Game game = modelMapper.map(gameAddDto, Game.class);
        gameRepository.save(game);
        System.out.printf("Added %s%n", game.getTitle());
    }

    @Override
    public void editGame(long id, BigDecimal price, double size) {
        Game game = gameRepository.findById(id)
                .orElse(null);
        if (game == null){
            System.out.println("Game not found");
            return;
        }else if (size <= 0 || price.compareTo(BigDecimal.ZERO) < 0){
            System.out.println("Enter a valid price/size");
            return;
        }else{
            game.setPrice(price);
            game.setSize(size);
            gameRepository.save(game);
            System.out.printf("Edited %s%n", game.getTitle());

        }
    }

    @Override
    public void deleteGame(long id) {
        Game game = gameRepository.findById(id)
                .orElse(null);
        if (game == null) {
            System.out.println("Game not found");
            return;
        }
        gameRepository.delete(game);
        System.out.printf("Deleted %s%n", game.getTitle());
    }

    @Override
    public List<AllGamesDto> findAllGames() {
        return gameRepository.findAll().stream()
                .map(game -> modelMapper.map(game, AllGamesDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DetailGameDto findGameByTitle(String title) {
        Game game = gameRepository.findGamesByTitle(title);
        DetailGameDto detailGameDto = null;
        if (game != null){
            detailGameDto = modelMapper.map(game, DetailGameDto.class);
        }
        return detailGameDto;
    }
}
