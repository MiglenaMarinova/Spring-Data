package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentsSeedDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {

    public static final String AGENTS_FILE_PATH = "src/main/resources/files/json/agents.json";
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;
    private final AgentRepository agentRepository;

    public AgentServiceImpl(Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, TownRepository townRepository, AgentRepository agentRepository) {
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
        this.agentRepository = agentRepository;
    }

    @Override
    public boolean areImported() {
        return agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(Path.of(AGENTS_FILE_PATH));
    }

    @Override
    public String importAgents() throws IOException {

        StringBuilder sb = new StringBuilder();

        if (agentRepository.count() == 0) {

            AgentsSeedDto[] agentsSeedDtos = gson.fromJson(readAgentsFromFile(), AgentsSeedDto[].class);

            Arrays.stream(agentsSeedDtos)
                    .filter(agentsSeedDto -> {
                        boolean isValid = validationUtil.isValid(agentsSeedDto);
                        boolean isUnique = agentRepository.findAllByFirstName(agentsSeedDto.getFirstName()).isEmpty();

                        if (isValid && isUnique){
                            sb.append(String.format("Successfully imported agent - %s %s",
                                            agentsSeedDto.getFirstName(), agentsSeedDto.getLastName()))
                                    .append(System.lineSeparator());
                        }else{
                            sb.append("Invalid agent").append(System.lineSeparator());
                        }
                        return (isValid && isUnique);
                    })
                    .map(agentsSeedDto -> {
                        Agent agent = modelMapper.map(agentsSeedDto, Agent.class);
                        Town town = townRepository.findAllByTownName(agentsSeedDto.getTown()).orElse(null);
                        if (town != null){
                            agent.setTown(town);
                        }
                        return agent;
                    }).forEach(agentRepository :: save);
        }
        return sb.toString().trim();
    }
}
