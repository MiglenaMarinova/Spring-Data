package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CitiesSeedDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    public static final String CITIES_FILE_PATH = "src/main/resources/files/json/cities.json";
    private final CityRepository cityRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;


    public CityServiceImpl(CityRepository cityRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(Path.of(CITIES_FILE_PATH));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder sb = new StringBuilder();

        if (cityRepository.count() == 0) {

            CitiesSeedDto[] citiesSeedDtos = gson.fromJson(readCitiesFileContent(), CitiesSeedDto[].class);
            Arrays.stream(citiesSeedDtos).filter(citiesSeedDto -> {
                        boolean isValid = validationUtil.isValid(citiesSeedDto);
                        boolean isUnique = cityRepository.findFirstByCityName(citiesSeedDto.getCityName()).isEmpty();
                        boolean isExist = countryRepository.findById(citiesSeedDto.getCountry()).isPresent();

                        if (isValid && isUnique && isExist) {
                            sb.append(String.format("Successfully imported city %s - %d",
                                    citiesSeedDto.getCityName(),
                                    citiesSeedDto.getPopulation())).append(System.lineSeparator());
                        } else {
                            sb.append("Invalid city").append(System.lineSeparator());
                        }
                        return (isValid && isUnique && isExist);
                    })
                    .map(citiesSeedDto -> {
                        City city = modelMapper.map(citiesSeedDto, City.class);
                        Optional<Country> country = countryRepository.findById(citiesSeedDto.getCountry());
                        if (country.isPresent()) {
                            city.setCountry(country.get());
                        }

                        return city;
                    }).forEach(cityRepository::save);
        }


        return sb.toString().trim();
    }

    @Override
    public City findCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }


}

