package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{

//    •	title – accepts char sequence (between 3 to 40 inclusive). The values are unique in the database.
//•	author - accepts char sequence (between 3 to 40 inclusive).
//•	description - a long and detailed description about the book with a character length value higher than or equal to 5.
//•	available – accepts a true or false, representing the availability status of the book.
//•	genre – String enumeration, one of the following – CLASSIC_LITERATURE, SCIENCE_FICTION, FANTASY
//•	rating – accepts number values that are positive.
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "available", nullable = false)
    private boolean available;
    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false)
    private Genre genre;
    @Column(name = "rating", nullable = false)
    private Double rating;

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
