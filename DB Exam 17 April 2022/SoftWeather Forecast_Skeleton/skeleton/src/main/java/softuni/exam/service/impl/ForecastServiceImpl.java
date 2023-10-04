package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import softuni.exam.models.dto.ExportForecastDto;
import softuni.exam.models.dto.ForecastsRootSeedDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ForecastServiceImpl implements ForecastService {
    private static final String FORECASTS_FILE_PATH = "src/main/resources/files/xml/forecasts.xml";

    private final ForecastRepository forecastRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
private final CityRepository cityRepository;
    private final CityService cityService;


    public ForecastServiceImpl(ForecastRepository forecastRepository,
                               ValidationUtil validationUtil, ModelMapper modelMapper,
                               XmlParser xmlParser, CityRepository cityRepository, CityService cityService) {
        this.forecastRepository = forecastRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.cityRepository = cityRepository;

        this.cityService = cityService;
    }

    @Override
    public boolean areImported() {
        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(FORECASTS_FILE_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {

        StringBuilder sb = new StringBuilder();

        if(forecastRepository.count() == 0){
            ForecastsRootSeedDto forecastsRootSeedDto = xmlParser
                    .fromFile(FORECASTS_FILE_PATH, ForecastsRootSeedDto.class);
            forecastsRootSeedDto.getForecasts().stream()
                    .filter(forecastSeedDto -> {
                        boolean isValid = validationUtil.isValid(forecastSeedDto);
                        Optional<City> city = cityRepository.findById(forecastSeedDto.getCity());
                        boolean isValidCity = city.isPresent();
                        boolean isNewRecord = false;
                        if (isValidCity){
                            isNewRecord = forecastRepository
                                    .findAllByDayOfWeekAndCity(forecastSeedDto.getDayOfWeek(), city.get()).isEmpty();
                        }

                        if (isValid && isValidCity && isNewRecord){
                            sb.append(String.format("Successfully imported forecast %s - %.0f",
                                    forecastSeedDto.getDayOfWeek(), forecastSeedDto.getMaxTemperature()))
                                    .append(System.lineSeparator());
                        } else {
                            sb.append("Invalid forecast").append(System.lineSeparator());
                        }
                        return (isValid && isValidCity && isNewRecord);
                    })
                    .map(forecastSeedDto -> {
                        Forecast forecast = modelMapper.map(forecastSeedDto, Forecast.class);
                        Optional<City> city = cityRepository.findById(forecastSeedDto.getCity());
                        if (city.isPresent()){
                            forecast.setCity(city.get());
                        }
                        return forecast;
                    }).forEach(forecastRepository::save);
        }
        return sb.toString().trim();
    }

    @Override
    public String exportForecasts() {
        StringBuilder sb = new StringBuilder();

//        List<ExportForecastDto> exportForecastDtos = this.forecastRepository
//                .findAllByCityPopulationAndDayOfWeek();
//
//        exportForecastDtos.forEach(exportForecastDto -> {
//            sb.append(String.format("City: %s:%n" +
//                            "   \t\t-min temperature: %.2f%n" +
//                            "   \t\t--max temperature: %.2f%n" +
//                            "   \t\t---sunrise: %s%n" +
//                            "   \t\t----sunset: %s",
//                    exportForecastDto.getCityName(),
//                    exportForecastDto.getMaxTemperature(),
//                    exportForecastDto.getMinTemperature(),
//                    exportForecastDto.getSunrise(),
//                    exportForecastDto.getSunset())).append(System.lineSeparator());
//        });

        forecastRepository.findAllByDayOfWeekAndCitizen()
                .forEach(forecasts -> {
                    sb.append(String.format("City: %s:%n" +
                            "   \t\t-min temperature: %.2f%n" +
                            "   \t\t--max temperature: %.2f%n" +
                            "   \t\t---sunrise: %s%n" +
                            "   \t\t----sunset: %s",
                            forecasts.getCity().getCityName(),
                            forecasts.getMinTemperature(),
                            forecasts.getMinTemperature(),
                            forecasts.getSunrise(),
                            forecasts.getSunset())).append(System.lineSeparator());
                });
        return sb.toString().trim();
    }
}
