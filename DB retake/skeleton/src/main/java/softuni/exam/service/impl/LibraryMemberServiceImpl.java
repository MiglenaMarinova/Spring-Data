package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.MembersSeedDto;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

// TODO: Implement all methods
@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {

    public static final String MEMBERS_FILE_PATH = "src/main/resources/files/json/library-members.json";

    private final LibraryMemberRepository libraryMemberRepository;

    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public LibraryMemberServiceImpl(LibraryMemberRepository libraryMemberRepository, ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper) {
        this.libraryMemberRepository = libraryMemberRepository;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return libraryMemberRepository.count() > 0;
    }

    @Override
    public String readLibraryMembersFileContent() throws IOException {
        return Files.readString(Path.of(MEMBERS_FILE_PATH));
    }

    @Override
    public String importLibraryMembers() throws IOException {

        StringBuilder sb = new StringBuilder();

        MembersSeedDto[] membersSeedDtos = gson.fromJson(readLibraryMembersFileContent(), MembersSeedDto[].class);

        Arrays.stream(membersSeedDtos)
                .filter(membersSeedDto -> {
                    boolean isValid = validationUtil.isValid(membersSeedDto);
                    boolean isUnique = libraryMemberRepository.findAllByPhoneNumber(membersSeedDto.getPhoneNumber()).isEmpty();

                    if(isValid && isUnique){
                        sb.append(String.format("Successfully imported library member %s - %s",
                                membersSeedDto.getFirstName(),
                                membersSeedDto.getLastName())).append(System.lineSeparator());
                    }else{
                        sb.append("Invalid library member").append(System.lineSeparator());
                    }
                    return (isValid && isUnique);
                }).map(membersSeedDto -> {
                    LibraryMember libraryMember = modelMapper.map(membersSeedDto, LibraryMember.class);
                    return libraryMember;
                }).forEach(libraryMemberRepository::save);


        return sb.toString().trim();
    }
}
