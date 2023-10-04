package com.example.football.service.impl;

import com.example.football.models.dto.TeamsSeedDto;
import com.example.football.models.dto.TownsSeedDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
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
public class TeamServiceImpl implements TeamService {

    public static final String TEAMS_FILE_PATH = "src/main/resources/files/json/teams.json";

    private final TeamRepository teamRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    public TeamServiceImpl(TeamRepository teamRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }


    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(TEAMS_FILE_PATH));
    }

    @Override
    public String importTeams() throws IOException {

        StringBuilder sb = new StringBuilder();

        if(teamRepository.count() == 0){

            TeamsSeedDto[] teamsSeedDtos = gson.fromJson(readTeamsFileContent(), TeamsSeedDto[].class);

            Arrays.stream(teamsSeedDtos)
                    .filter(teamsSeedDto -> {
                        boolean isValid = validationUtil.isValid(teamsSeedDto);
                        boolean isUnique = teamRepository.findAllByName(teamsSeedDto.getName()).isEmpty();

                        if (isValid && isUnique){
                            sb.append(String.format("Successfully imported Team %s - %d",
                                    teamsSeedDto.getName(),
                                    teamsSeedDto.getFanBase())).append(System.lineSeparator());
                        }else{
                            sb.append("Invalid team").append(System.lineSeparator());
                        }
                        return (isValid && isUnique);
                    }).map(teamsSeedDto -> {
                        Team team = modelMapper.map(teamsSeedDto, Team.class);
//                        team.setTown(townRepository.findAllByName(teamsSeedDto.getTownName()).get());
                        Town town = townRepository.findAllByName(teamsSeedDto.getTownName()).orElse(null);
                        if (town != null){
                            team.setTown(town);
                        }
                        return team;
                    }).forEach(teamRepository::save);

        }


        //• name – accepts char sequences as values where their character length value higher than or equal to 3.
// The values are unique in the database.



        return sb.toString().trim();
    }
}
