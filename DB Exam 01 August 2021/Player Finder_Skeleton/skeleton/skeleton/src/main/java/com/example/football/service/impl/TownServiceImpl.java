package com.example.football.service.impl;

import com.example.football.models.dto.TownsSeedDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


//ToDo - Implement all methods
@Service
public class TownServiceImpl implements TownService {

    public static final String TOWNS_FILE_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();

        TownsSeedDto[] townsSeedDtos = gson.fromJson(readTownsFileContent(), TownsSeedDto[].class);

        Arrays.stream(townsSeedDtos)
                .filter(townsSeedDto -> {
                    boolean isValid = validationUtil.isValid(townsSeedDto);
                    boolean isUnique = townRepository.findAllByName(townsSeedDto.getName()).isEmpty();

                    if (isValid && isUnique){
                        sb.append(String.format("Successfully imported Town %s - %d",
                                townsSeedDto.getName(),
                                townsSeedDto.getPopulation())).append(System.lineSeparator());
                    }else{
                        sb.append("Invalid town").append(System.lineSeparator());
                    }
                    return (isValid && isUnique);
                }).map(townsSeedDto -> {
                    Town town = modelMapper.map(townsSeedDto, Town.class);
                    return town;
                }).forEach(townRepository::save);


        return sb.toString().trim();
    }
}
