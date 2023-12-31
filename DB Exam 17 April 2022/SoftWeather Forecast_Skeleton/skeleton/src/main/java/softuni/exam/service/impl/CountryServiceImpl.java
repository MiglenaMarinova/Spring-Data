package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountriesSeedDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class CountryServiceImpl implements CountryService {
    public static final String COUNTRIES_FILE_PATH = "src/main/resources/files/json/countries.json";

    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CountryServiceImpl(CountryRepository countryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(Path.of(COUNTRIES_FILE_PATH));
    }

    @Override
    public String importCountries() throws IOException {

        StringBuilder sb = new StringBuilder();

        if(countryRepository.count() == 0){

            CountriesSeedDto[] countriesSeedDtos = gson.fromJson(readCountriesFromFile(), CountriesSeedDto[].class);

            Arrays.stream(countriesSeedDtos)
                    .filter(countriesSeedDto -> {
                        boolean isValid = validationUtil.isValid(countriesSeedDto);
                        boolean isUnique = countryRepository.findFirstByCountryName(countriesSeedDto.getCountryName()).isEmpty();

                        if (isUnique && isValid){
                            sb.append(String.format("Successfully imported country %s - %s",
                                    countriesSeedDto.getCountryName(),
                                    countriesSeedDto.getCurrency())).append(System.lineSeparator());
                        }else{
                            sb.append("Invalid country");
                        }
                        return (isValid && isUnique);
                    })
                    .map(countriesSeedDto -> modelMapper.map(countriesSeedDto, Country.class))
                    .forEach(countryRepository::save);
        }


        return sb.toString().trim();
    }
}
