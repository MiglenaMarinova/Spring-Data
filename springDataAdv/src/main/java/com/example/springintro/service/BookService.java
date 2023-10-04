package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllBooksWithAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllBooksWithLessThan5000Copies();

    List<String> findAllBookWithPriceLessThan4And40();

    List<String> findAllBookNotReleasedInYear(int year);

    List<String> findAllBooksReleasedBeforeDate(LocalDate localDate);

    List<String> findAllBookTheirTitleContainingString(String givenString);

    List<String> findAllBookTitleFromAuthorWhichLastNameStartsWithStr(String startStr);

    int findAllBookWithTitleLengthMoreThan(int titleLength);

    Book  findBook(String bookTitle);
}
