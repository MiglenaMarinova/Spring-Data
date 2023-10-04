package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.ExportUserWithTheirPostDto;
import softuni.exam.instagraphlite.models.dto.UsersSeedDto;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final String USER_FILE_PATH = "src/main/resources/files/users.json";

    private final UserRepository userRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final PictureRepository pictureRepository;


    public UserServiceImpl(UserRepository userRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, PictureRepository pictureRepository) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(USER_FILE_PATH));
    }

    @Override
    public String importUsers() throws IOException {

        StringBuilder sb = new StringBuilder();

        UsersSeedDto[] usersSeedDtos = gson.fromJson(readFromFileContent(), UsersSeedDto[].class);

        if (userRepository.count() == 0) {

            Arrays.stream(usersSeedDtos)
                    .filter(usersSeedDto -> {
                        boolean isValid = validationUtil.isValid(usersSeedDto);
                        boolean isUnique = userRepository.findAllByUsername(usersSeedDto.getUsername()).isEmpty();
                        boolean isValidPic = pictureRepository.findAllByPath(usersSeedDto.getProfilePicture()).isPresent();

                        if (isUnique && isValidPic && isValid) {
                            sb.append(String.format("Successfully imported User: %s",
                                    usersSeedDto.getUsername())).append(System.lineSeparator());
                        } else {
                            sb.append("Invalid User").append(System.lineSeparator());
                        }

                        return (isUnique && isValidPic && isValid);
                    })
                    .map(usersSeedDto -> {
                        User user = modelMapper.map(usersSeedDto, User.class);
                        user.setProfilePicture(pictureRepository.findAllByPath(usersSeedDto.getProfilePicture()).get());
                        return user;
                    }).forEach(userRepository::save);

        }
        return sb.toString().trim();
    }

    @Override
    public String exportUsersWithTheirPosts() {

        StringBuilder sb = new StringBuilder();

        List<User> users = userRepository.findAllByPosts().stream().collect(Collectors.toList());

        System.out.println();

        users.stream()
                .forEach(u -> sb.append(String.format("User: %s%n" +
                                "Post count: %d%n" +
                                "==Post Details:%n" +
                                "----Caption: %s%n" +
                                "----Picture Size: %.2f"
                        )));


        return sb.toString().trim();
    }
}
