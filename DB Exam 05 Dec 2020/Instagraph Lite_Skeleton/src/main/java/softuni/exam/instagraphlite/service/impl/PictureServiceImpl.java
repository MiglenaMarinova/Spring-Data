package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PicturesSeedDto;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PictureServiceImpl implements PictureService {
    public static final String PICTURE_FILE_PATH = "src/main/resources/files/pictures.json";
    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PICTURE_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {

        StringBuilder sb = new StringBuilder();

        PicturesSeedDto[] picturesSeedDtos = gson.fromJson(readFromFileContent(), PicturesSeedDto[].class);

        if (pictureRepository.count() == 0){

            Arrays.stream(picturesSeedDtos)
                    .filter(picturesSeedDto -> {
                        boolean isValid = validationUtil.isValid(picturesSeedDto);
                        boolean isUnique = pictureRepository.findAllByPath(picturesSeedDto.getPath()).isEmpty();

                        if (isUnique && isValid){
                            sb.append(String.format("Successfully imported Picture, with size %.2f",
                                    picturesSeedDto.getSize())).append(System.lineSeparator());
                        }else{
                            sb.append("Invalid Picture").append(System.lineSeparator());
                        }
                        return (isUnique && isValid);
                    })
                    .map(picturesSeedDto -> {
                        Picture picture = modelMapper.map(picturesSeedDto, Picture.class);
                        return picture;
                    }).forEach(pictureRepository::save);
        }

        return sb.toString().trim();
    }

    @Override
    public String exportPictures() {
        return null;
    }
}
