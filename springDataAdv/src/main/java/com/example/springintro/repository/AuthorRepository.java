package com.example.springintro.repository;

import com.example.springintro.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllByBooksSizeDESC();

    List<Author> findAuthorByFirstNameEndingWith(String endString);



    @Query("SELECT a.firstName AS firstName, " +
            "a.lastName AS lastName, " +
            "SUM(b.copies) AS totalSumCopies " +
            "FROM Author a " +
            "JOIN a.books AS b " +
            "GROUP BY b.author " +
            "ORDER BY totalSumCopies DESC")
    List<String> findTotalCopies();


}
