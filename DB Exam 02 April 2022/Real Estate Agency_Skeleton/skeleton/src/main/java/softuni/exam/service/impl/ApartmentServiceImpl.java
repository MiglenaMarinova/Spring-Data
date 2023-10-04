package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentsRootSeedDto;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    public static final String APARTMENTS_FILE_PATH = "src/main/resources/files/xml/apartments.xml";

    private final ApartmentRepository apartmentRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;



    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, XmlParser xmlParser,
                                ValidationUtil validationUtil, ModelMapper modelMapper,
                                TownRepository townRepository) {
        this.apartmentRepository = apartmentRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(Path.of(APARTMENTS_FILE_PATH));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {

        StringBuilder sb = new StringBuilder();

        ApartmentsRootSeedDto apartmentsRootSeedDto = xmlParser
                .fromFile(APARTMENTS_FILE_PATH, ApartmentsRootSeedDto.class);

        apartmentsRootSeedDto.getApartments().stream()
                .filter(apartmentSeedDto -> {
                    boolean isValid = validationUtil.isValid(apartmentSeedDto);
                    boolean isUnique = apartmentRepository
                            .findAllByAreaAndTown_TownName(apartmentSeedDto.getArea(), apartmentSeedDto.getTown()).isEmpty();

                    if (isValid && isUnique){
                        sb.append(String.format("Successfully imported apartment %s - %.2f",
                                apartmentSeedDto.getApartmentType(), apartmentSeedDto.getArea()))
                                .append(System.lineSeparator());
                    }else{
                        sb.append("Invalid apartment").append(System.lineSeparator());
                    }
                    return (isValid && isUnique);
                }).map(apartmentSeedDto -> {
                    Apartment apartment = modelMapper.map(apartmentSeedDto, Apartment.class);
                    Town town = townRepository.findAllByTownName(apartmentSeedDto.getTown()).orElse(null);
                    apartment.setTown(town);
                    apartment.setApartmentType(apartmentSeedDto.getApartmentType());

                    return apartment;
                }).forEach(apartmentRepository::save);

        return sb.toString().trim();
    }
}
