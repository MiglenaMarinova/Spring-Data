package com.example.football.service.impl;

import com.example.football.models.dto.StatRootSeedDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//ToDo - Implement all methods
@Service
public class StatServiceImpl implements StatService {

    public static final String STAT_FILE_PATH = "src/main/resources/files/xml/stats.xml";

    private final StatRepository statRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public StatServiceImpl(StatRepository statRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.statRepository = statRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(STAT_FILE_PATH));
    }

    @Override
    public String importStats() throws JAXBException, FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        if (statRepository.count() == 0){

            StatRootSeedDto statRootSeedDto = xmlParser.fromFile(STAT_FILE_PATH, StatRootSeedDto.class);

            statRootSeedDto.getStats().stream()
                    .filter(statsSeedDto -> {
                        boolean isValid = validationUtil.isValid(statsSeedDto);
                       boolean isValidStat = statRepository.findAllByPassingAndShootingAndEndurance(
                               statsSeedDto.getPassing(), statsSeedDto.getShooting(), statsSeedDto.getEndurance()).isEmpty();
                       if (isValid && isValidStat){
                           sb.append(String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                                   statsSeedDto.getPassing(),
                                   statsSeedDto.getShooting(),
                                   statsSeedDto.getEndurance())).append(System.lineSeparator());
                       }else{
                           sb.append("Invalid Stat").append(System.lineSeparator());
                       }
                       return (isValid && isValidStat);

                    }).map(statsSeedDto -> modelMapper.map(statsSeedDto, Stat.class))
                    .forEach(statRepository::save);

        }

        return sb.toString().trim();
    }
}
