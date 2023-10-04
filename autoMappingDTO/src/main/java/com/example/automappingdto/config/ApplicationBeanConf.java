package com.example.automappingdto.config;

import com.example.automappingdto.models.dto.GameAddDto;
import com.example.automappingdto.models.entities.Game;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConf {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDate> localDateConverter = new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
                return LocalDate.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            }
        };
        modelMapper.addConverter(localDateConverter);
        modelMapper.typeMap(GameAddDto.class, Game.class)
                .addMappings(mapping ->{
                    mapping.map(GameAddDto::getImageThumbnail,
                            Game::setImageThumbnail);
                    mapping.using(localDateConverter).map(GameAddDto::getReleaseDate, Game::setReleaseDate);
                });
        return modelMapper;
    }

}
