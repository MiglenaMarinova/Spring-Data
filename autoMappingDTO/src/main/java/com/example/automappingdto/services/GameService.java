package com.example.automappingdto.services;

import com.example.automappingdto.models.dto.AllGamesDto;
import com.example.automappingdto.models.dto.DetailGameDto;
import com.example.automappingdto.models.dto.GameAddDto;

import java.math.BigDecimal;
import java.util.List;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    void editGame(long id, BigDecimal price, double size);

    void deleteGame(long id);


    List<AllGamesDto> findAllGames();

    DetailGameDto findGameByTitle(String title);
}
