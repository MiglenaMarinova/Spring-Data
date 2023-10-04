package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PostsRootSeedDto;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    public static final String POST_FILE_PATH = "src/main/resources/files/posts.xml";

    private final PostRepository postRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, XmlParser xmlParser,
                           ModelMapper modelMapper, ValidationUtil validationUtil,
                           PictureRepository pictureRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean areImported() {
        return postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(POST_FILE_PATH));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {

        StringBuilder sb = new StringBuilder();

        PostsRootSeedDto postsRootSeedDto = xmlParser.fromFile(POST_FILE_PATH, PostsRootSeedDto.class);

        postsRootSeedDto.getPosts().stream()
                .filter(postSeedDto -> {
                    boolean isValid = validationUtil.isValid(postSeedDto);
                    boolean isValidUser = userRepository.findAllByUsername(postSeedDto.getUser().getUsername()).isPresent();
                    boolean isValidPic = pictureRepository.findAllByPath(postSeedDto.getPicture().getPath()).isPresent();

                    if(isValidPic && isValid && isValidUser){
                        sb.append(String.format("Successfully imported Post, made by %s",
                                postSeedDto.getUser().getUsername())).append(System.lineSeparator());
                    }else {
                        sb.append("Invalid Post").append(System.lineSeparator());
                    }

                    return (isValidPic && isValid && isValidUser);
                })
                .map(postSeedDto -> {
                    Post post = modelMapper.map(postSeedDto, Post.class);
                    post.setUser(userRepository.findAllByUsername(postSeedDto.getUser().getUsername()).get());
                    post.setPicture(pictureRepository.findAllByPath(postSeedDto.getPicture().getPath()).get());

                    return post;
                }).forEach(postRepository :: save);

        return sb.toString().trim();
    }
}
