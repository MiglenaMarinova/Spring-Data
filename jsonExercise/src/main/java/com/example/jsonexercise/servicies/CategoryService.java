package com.example.jsonexercise.servicies;

import com.example.jsonexercise.models.dtos.CategoryProductsInfoDto;
import com.example.jsonexercise.models.entities.Category;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> findRandomCategories();

    List<CategoryProductsInfoDto> findAllCategoriesByProductCount();
}
