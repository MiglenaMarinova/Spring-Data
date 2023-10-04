package exam.service.impl;

import exam.model.dto.TownsRootSeedDto;
import exam.model.entity.Town;
import exam.repository.TownRepository;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class TownServiceImpl implements TownService {

    public static final String TOWNS_FILE_PATH = "src/main/resources/files/xml/towns.xml";

    private final TownRepository townRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, XmlParser xmlParser,
                           ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.xmlParser = xmlParser;
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
    public String importTowns() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        //    •	name – accepts char sequences as values where their character length value higher than or equal to 2.
//    The values are unique in the database.
        TownsRootSeedDto townsRootSeedDto = xmlParser.fromFile(TOWNS_FILE_PATH, TownsRootSeedDto.class);

        townsRootSeedDto.getTowns().stream()
                .filter(townsSeedDto -> {
                    boolean isValid = validationUtil.isValid(townsSeedDto);
                    boolean isUnique = townRepository.findAllByName(townsSeedDto.getName()).isEmpty();

                    if (isValid && isUnique){
                        sb.append(String.format("Successfully imported Town %s",
                                townsSeedDto.getName())).append(System.lineSeparator());
                    }else{
                        sb.append("Invalid Town").append(System.lineSeparator());
                    }
                    return (isValid && isUnique);
                })
                .map(townsSeedDto -> {
                    Town town = modelMapper.map(townsSeedDto, Town.class);
                    return town;
                }).forEach(townRepository::save);


        return sb.toString().trim();
    }
}
