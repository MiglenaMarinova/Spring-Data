package com.example.xmlexercise.repository;

import com.example.xmlexercise.model.dto.CategoryNameWithProductsDto;
import com.example.xmlexercise.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new com.example.xmlexercise.model.dto.CategoryNameWithProductsDto " +
            "(c.name,COUNT (p.id), AVG (p.price), SUM (p.price)) " +
            "FROM Product p " +
            "JOIN p.categories c " +
            "GROUP BY c.id " +
            "ORDER BY count (p.id) DESC")
    List<CategoryNameWithProductsDto> findCategoriesByProductsOrderByNumberOfProducts();



}
