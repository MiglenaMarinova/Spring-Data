package com.example.jsonexercise;

import com.example.jsonexercise.models.dtos.CategoryProductsInfoDto;
import com.example.jsonexercise.models.dtos.ProductNamePriceSellerDto;
import com.example.jsonexercise.models.dtos.UserSoldDto;
import com.example.jsonexercise.servicies.CategoryService;
import com.example.jsonexercise.servicies.ProductService;
import com.example.jsonexercise.servicies.UserService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Component
public class CommandLineRunnerImp implements CommandLineRunner {
    public static final String OUTPUT_PATH = "src/main/resources/files/output/";
    public static final String PRODUCTS_IN_RANGE_NAME = "products-in-range.json";
    public static final String USERS_WITH_SOLD_PRODUCTS = "users-with-sold-products.json";
    public static final String CATEGORY_PRODUCT_INFO = "category-product-info.json";
    public static final String USER_AND_PRODUCT = "user-and-product.json";
    private final BufferedReader bufferedReader;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final Gson gson;

    public CommandLineRunnerImp(CategoryService categoryService, UserService userService, ProductService productService, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        seedData();

        System.out.println("Enter exercise number");
        int exNum = Integer.parseInt(bufferedReader.readLine());

        switch (exNum) {
            case 1 -> productsInRange();
            case 2 -> soldProducts();
            case 3 -> categoryByProductCount();
            case 4 -> usersAndProducts();
        }
    }

    private void usersAndProducts() throws IOException {
//            userService.usersAndProducts();
        String js = gson.toJson(userService.usersAndProducts());
        writeToFile(OUTPUT_PATH + USER_AND_PRODUCT, js);
    }

    private void categoryByProductCount() throws IOException {
        List<CategoryProductsInfoDto> categoryProductsInfoDtos = categoryService.findAllCategoriesByProductCount();

        String content = gson.toJson(categoryProductsInfoDtos);

        writeToFile(OUTPUT_PATH + CATEGORY_PRODUCT_INFO, content);
    }

    private void soldProducts() throws IOException {
        List<UserSoldDto> userSoldDtos = userService.findUserWithAtLestOneSoldProduct();

        String content = gson.toJson(userSoldDtos);

        writeToFile(OUTPUT_PATH + USERS_WITH_SOLD_PRODUCTS, content);
    }

    private void productsInRange() throws IOException {

        List<ProductNamePriceSellerDto> productDtos = productService
                .findAllProductsInRange(BigDecimal.valueOf(500L), BigDecimal.valueOf(1000L));

        String content = gson.toJson(productDtos);

        writeToFile(OUTPUT_PATH + PRODUCTS_IN_RANGE_NAME, content);
    }

    private void writeToFile(String filePath, String content) throws IOException {

        Files.write(Path.of(filePath),
                Collections.singleton(content));
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        userService.seedUsers();
        productService.seedProducts();
    }
}
