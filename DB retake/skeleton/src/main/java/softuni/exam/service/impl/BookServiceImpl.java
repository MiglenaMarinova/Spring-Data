package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BooksSeedDto;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


// TODO: Implement all methods
@Service
public class BookServiceImpl implements BookService {

    public static final String BOOKS_FILE_PATH = "src/main/resources/files/json/books.json";

    private final BookRepository bookRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean areImported() {
        return bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(BOOKS_FILE_PATH));
    }

    @Override
    public String importBooks() throws IOException {

        StringBuilder sb = new StringBuilder();

        BooksSeedDto[] booksSeedDtos = gson.fromJson(readBooksFromFile(), BooksSeedDto[].class);

        Arrays.stream(booksSeedDtos)
                .filter(booksSeedDto -> {
                    boolean isValid = validationUtil.isValid(booksSeedDto);
                    boolean isUnique = bookRepository.findAllByTitle(booksSeedDto.getTitle()).isEmpty();

                    if(isUnique && isValid){
                        sb.append(String.format("Successfully imported book %s - %s",
                                booksSeedDto.getAuthor(),
                                booksSeedDto.getTitle())).append(System.lineSeparator());
                    }else{
                        sb.append("Invalid book").append(System.lineSeparator());
                    }

                    return (isUnique && isValid);
                }).map(booksSeedDto -> {
                    Book book = modelMapper.map(booksSeedDto, Book.class);
                    book.setGenre(booksSeedDto.getGenre());

                    return book;
                }).forEach(bookRepository::save);



        return sb.toString().trim();
    }
}
