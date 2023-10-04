package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

//        printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
//        printAllAuthorsAndNumberOfTheirBooks();
//        printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

//        printAllBooksWithAgeRestriction();
//        printAllGoldenEditionBooks();
//        printAllBooksWithPrice();
//        printAllBookNotReleaseInYear();
//        printAllBooksReleasedBeforeDate();
//        printAllAuthorsNamesEndingWith();
//        printAllBooksTitleContainingString();
//        printAllBooksTitleWrittenByAuthorLastNameStartsWith();
//        printCountOfBookWithTitleLengthLongerThan();
//        printTotalCountOfBookCopiesByAuthor();
//        printBookInfoFor();
        printTotalCopies();


    }

    private void printTotalCopies() {
        authorService.findTotalCopies()
                .forEach(System.out::println);
    }

    private void printBookInfoFor() throws IOException {
        System.out.println("Enter book title:");
        String bookTitle = bufferedReader.readLine();
        Book book = bookService.findBook(bookTitle);
        System.out.printf("%s %s %s %.2f",
                book.getTitle(),
                book.getEditionType().name(),
                book.getAgeRestriction().name(),
                book.getPrice());
    }

    private void printTotalCountOfBookCopiesByAuthor() {
        authorService.findTotalSumOfCopiesOfBooks()
                .forEach(System.out::println);
    }

    private void printCountOfBookWithTitleLengthLongerThan() throws IOException {
        System.out.println("Enter length:");
        int titleLength = Integer.parseInt(bufferedReader.readLine());
        System.out.println(bookService
                .findAllBookWithTitleLengthMoreThan(titleLength));
    }

    private void printAllBooksTitleWrittenByAuthorLastNameStartsWith() throws IOException {
        System.out.println("Enter start string:");
        String startStr = bufferedReader.readLine();
        bookService
                .findAllBookTitleFromAuthorWhichLastNameStartsWithStr(startStr)
                .forEach(System.out::println);
    }

    private void printAllBooksTitleContainingString() throws IOException {
        System.out.println("Enter string:");
        String givenString = bufferedReader.readLine();
        bookService
                .findAllBookTheirTitleContainingString(givenString)
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesEndingWith() throws IOException {
        System.out.println("Enter edn string:");
        String endStr = bufferedReader.readLine();

        authorService.
                findAllAuthorsTheirNamesEndingWithStr(endStr)
                .forEach(System.out::println);
    }

    private void printAllBooksReleasedBeforeDate() throws IOException {
        System.out.println("Enter Date In Format dd-MM-yyyy:");
        LocalDate localDate = LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        bookService
                .findAllBooksReleasedBeforeDate(localDate)
                .forEach(System.out::println);
    }

    private void printAllBookNotReleaseInYear() throws IOException {
        System.out.println("Enter Year");
        int year = Integer.parseInt(bufferedReader.readLine());
        bookService
                .findAllBookNotReleasedInYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksWithPrice() {
        bookService
                .findAllBookWithPriceLessThan4And40()
                .forEach(System.out::println);
    }

    private void printAllGoldenEditionBooks() {
        bookService
                .findAllBooksWithLessThan5000Copies()
                .forEach(System.out::println);
    }

    private void printAllBooksWithAgeRestriction() throws IOException {
        System.out.println("Enter age restriction:");
        AgeRestriction ageRestriction = AgeRestriction.valueOf(bufferedReader.readLine().toUpperCase());

        bookService
                .findAllBooksWithAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
