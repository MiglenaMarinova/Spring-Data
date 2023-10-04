package com.example.jsonexercise.repositories;

import com.example.jsonexercise.models.dtos.CategoryProductsInfoDto;
import com.example.jsonexercise.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new com.example.jsonexercise.models.dtos.CategoryProductsInfoDto " +
            "(c.name, count (p.id), avg (p.price), sum (p.price)) " +
            "FROM Product p " +
            "JOIN p.categories c " +
            "GROUP BY c.id " +
            "ORDER BY count (p.id) DESC")
    List<CategoryProductsInfoDto> findCategoriesByProductsCount();

}
