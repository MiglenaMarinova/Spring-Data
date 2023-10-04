package com.example.jsonexercise.servicies.impl;

import com.example.jsonexercise.models.dtos.CategoryProductsInfoDto;
import com.example.jsonexercise.models.dtos.CategorySeedDto;
import com.example.jsonexercise.models.entities.Category;
import com.example.jsonexercise.repositories.CategoryRepository;
import com.example.jsonexercise.servicies.CategoryService;
import com.example.jsonexercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.jsonexercise.constant.GlobalConstant.RESOURCES_FILE_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final String CATEGORIES_FILE_NAME = "categories.json";
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(Gson gson, ModelMapper modelMapper, CategoryRepository categoryRepository, ValidationUtil validationUtil) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories() throws IOException {

        if (categoryRepository.count() > 0){
            return;
        }

        String fileContent =Files
                .readString(Path.of(RESOURCES_FILE_PATH + CATEGORIES_FILE_NAME));

        CategorySeedDto[] categorySeedDtos = gson.fromJson(fileContent, CategorySeedDto[].class);

        Arrays.stream(categorySeedDtos)
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(categoryRepository::save);

    }

    @Override
    public Set<Category> findRandomCategories() {
        Set<Category> categorySet = new HashSet<>();
        int catCount = ThreadLocalRandom.current().nextInt(1, 3);
        long totalCategoryCount = categoryRepository.count();

        for (int i = 0; i < catCount; i++) {
            long randomId = ThreadLocalRandom
                    .current().nextLong(1, totalCategoryCount + 1);
            categorySet.add(categoryRepository.findById(randomId).orElse(null));
        }
        return categorySet;
    }

    @Override
    public List<CategoryProductsInfoDto> findAllCategoriesByProductCount() {
        return categoryRepository.findCategoriesByProductsCount();
    }
}
