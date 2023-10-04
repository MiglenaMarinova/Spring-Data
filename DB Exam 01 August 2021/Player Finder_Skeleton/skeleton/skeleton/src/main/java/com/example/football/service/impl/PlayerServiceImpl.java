package com.example.football.service.impl;

import com.example.football.models.dto.ExportPlayerDto;
import com.example.football.models.dto.PlayersRootSeedDto;
import com.example.football.models.entity.Player;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

//ToDo - Implement all methods
@Service
public class PlayerServiceImpl implements PlayerService {

    public static final String PLAYERS_FILE_PATH = "src/main/resources/files/xml/players.xml";

    private final PlayerRepository playerRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final TownRepository townRepository;
    private final TeamRepository teamRepository;
    private final StatRepository statRepository;


    public PlayerServiceImpl(PlayerRepository playerRepository, XmlParser xmlParser, ModelMapper modelMapper,
                             ValidationUtil validationUtil, TownRepository townRepository,
                             TeamRepository teamRepository, StatRepository statRepository) {
        this.playerRepository = playerRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
        this.statRepository = statRepository;
    }


    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(PLAYERS_FILE_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        PlayersRootSeedDto playersRootSeedDto = xmlParser.fromFile(PLAYERS_FILE_PATH, PlayersRootSeedDto.class);

        playersRootSeedDto.getPlayers().stream()
                .filter(playersSeedDto -> {
                    boolean isValid = validationUtil.isValid(playersSeedDto);
                    boolean isUnique = playerRepository.findAllByEmail(playersSeedDto.getEmail()).isEmpty();

                    if (isValid && isUnique) {
                        sb.append(String.format("Successfully imported Player %s %s - %s",
                                playersSeedDto.getFirsName(),
                                playersSeedDto.getLastName(),
                                playersSeedDto.getPosition())).append(System.lineSeparator());
                    } else {
                        sb.append("Invalid player").append(System.lineSeparator());
                    }
                    return (isValid && isUnique);
                })
                .map(playersSeedDto -> {
                    Player player = modelMapper.map(playersSeedDto, Player.class);
                    player.setTown(townRepository.findAllByName(playersSeedDto.getTown().getName()).get());
                    player.setTeam(teamRepository.findAllByName(playersSeedDto.getTeam().getName()).get());
                    player.setStat(statRepository.findById(playersSeedDto.getStat().getId()).get());

                    return player;
                })
                .forEach(playerRepository::save);


        return sb.toString().trim();
    }

    @Override
    public String exportBestPlayers() {
        StringBuilder sb = new StringBuilder();

        playerRepository.findAllByNamePositionStadiumTeam().stream()
                .forEach(exportPlayerDto -> {
                    sb.append(String.format("Player - %s %s%n" +
                                    "   Position - %s%n" +
                                    "   Team - %s%n" +
                                    "   Stadium - %s",
                            exportPlayerDto.getFirstName(),
                            exportPlayerDto.getLastName(),
                            exportPlayerDto.getPosition().name(),
                            exportPlayerDto.getTeamName(),
                            exportPlayerDto.getStadiumName())).append(System.lineSeparator());
                });


        return sb.toString().trim();
    }
}
