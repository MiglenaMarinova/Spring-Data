package com.example.xmlexercise;

import com.example.xmlexercise.model.dto.*;
import com.example.xmlexercise.service.CategoryService;
import com.example.xmlexercise.service.ProductService;
import com.example.xmlexercise.service.UserService;
import com.example.xmlexercise.util.XmlParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    public static final String RESOURCES_FILE_PATH = "src/main/resources/files/";
    public static final String CATEGORIES_FILE_NAME = "categories.xml";
    public static final String USERS_FILE_NAME = "users.xml";
    public static final String PRODUCTS_FILE_NAME = "products.xml";
    public static final String OUTPUT_FILE_PATH = "src/main/resources/files/output/";
    public static final String PRODUCT_IN_RAGE_FILE_NAME = "product-in-range.xml";
    public static final String USERS_WITH_SOLD_PRODUCTS = "users-with-sold-products.xml";
    public static final String CATEGORIES_BY_PRODUCTS_COUNT = "categories-by-product-count.xml";
    public static final String USER_PRODUCT_COUNT = "user-product-count.xml";
    private final XmlParser xmlParser;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;


    public CommandLineRunnerImpl(XmlParser xmlParser, CategoryService categoryService, UserService userService, ProductService productService) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        
        seedData();

        System.out.println("Choose exercise number");

        int exNum = Integer.parseInt(bufferedReader.readLine());

        switch (exNum){
            case 1 -> productsInRange();
            case 2 -> successfullySoldProducts();
            case 3 -> categoriesByProductsCount();
            case 4 -> usersAndProducts();
        }

    }

    private void usersAndProducts() throws JAXBException {
        UsersProductsView4Dto usersProductsView4Dto = userService.getAllUsersWithAtLeastOneSoldProduct();

        xmlParser.writeToFile(OUTPUT_FILE_PATH + USER_PRODUCT_COUNT, usersProductsView4Dto);


    }

    private void categoriesByProductsCount() throws JAXBException {
        CategoryRootViewDto categoryRootViewDto = categoryService.findCategoryByProductCount();

        xmlParser.writeToFile(OUTPUT_FILE_PATH + CATEGORIES_BY_PRODUCTS_COUNT, categoryRootViewDto);
    }

    private void successfullySoldProducts() throws JAXBException {
        UsersViewWithSoldProductsDto usersViewWithSoldProductsDto =
                userService.findAllUsersWithSoldProducts();

        xmlParser.writeToFile(OUTPUT_FILE_PATH + USERS_WITH_SOLD_PRODUCTS, usersViewWithSoldProductsDto);
    }

    private void productsInRange() throws JAXBException {
        ProductViewRootDto productViewRootDto =
                productService.findProductWithPriceInRangeAndBuyer();

        xmlParser.writeToFile(OUTPUT_FILE_PATH + PRODUCT_IN_RAGE_FILE_NAME,
                productViewRootDto);
    }

    private void seedData() throws JAXBException, FileNotFoundException {
        if (categoryService.getEntityCount()==0){

            CategorySeedRootDto categorySeedRootDto =
                    xmlParser.fromFile(RESOURCES_FILE_PATH + CATEGORIES_FILE_NAME, CategorySeedRootDto.class);

            categoryService.seedCategories(categorySeedRootDto.getCategories());
        }

        if(userService.getEntityCount() == 0){

            UserSeedRootDto userSeedRootDto =
                    xmlParser.fromFile(RESOURCES_FILE_PATH + USERS_FILE_NAME, UserSeedRootDto.class);

            userService.seedUsers(userSeedRootDto.getUsers());
        }

        if (productService.getEntityCount() == 0){

            ProductSeedRootDto productSeedRootDto =
                    xmlParser.fromFile(RESOURCES_FILE_PATH + PRODUCTS_FILE_NAME, ProductSeedRootDto.class);

            productService.seedProducts(productSeedRootDto.getProducts());

        }
    }
}
