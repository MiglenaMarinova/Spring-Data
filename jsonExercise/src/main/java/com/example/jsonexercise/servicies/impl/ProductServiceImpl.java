package com.example.jsonexercise.servicies.impl;

import com.example.jsonexercise.models.dtos.ProductNamePriceSellerDto;
import com.example.jsonexercise.models.dtos.ProductSeedDto;
import com.example.jsonexercise.models.entities.Product;
import com.example.jsonexercise.repositories.ProductRepository;
import com.example.jsonexercise.servicies.CategoryService;
import com.example.jsonexercise.servicies.ProductService;
import com.example.jsonexercise.servicies.UserService;
import com.example.jsonexercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.jsonexercise.constant.GlobalConstant.RESOURCES_FILE_PATH;
import static java.nio.file.Files.readAllLines;

@Service
public class ProductServiceImpl implements ProductService {
    public static final String PRODUCTS_FILE_NAME = "products.json";
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(CategoryService categoryService, UserService userService,
                              ProductRepository productRepository, Gson gson,
                              ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productRepository = productRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() > 0) {
            return;
        }
        String productsInfo = Files.readString(Path.of(RESOURCES_FILE_PATH + PRODUCTS_FILE_NAME));
        ProductSeedDto[] productSeedDtos = gson.fromJson(productsInfo, ProductSeedDto[].class);
        Arrays.stream(productSeedDtos)
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(userService.findRandomUser());

                    if (product.getPrice().compareTo(BigDecimal.valueOf(700L)) < 0) {
                        product.setBuyer(userService.findRandomUser());
                    }
                    product.setCategories(categoryService.findRandomCategories());

                    return product;
                })
                .forEach(productRepository::save);

    }

    @Override
    public List<ProductNamePriceSellerDto> findAllProductsInRange(BigDecimal lower, BigDecimal upper) {
        return productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(lower, upper)
                .stream()
                .map(product -> {
                    ProductNamePriceSellerDto productNamePriceSellerDto = modelMapper
                            .map(product, ProductNamePriceSellerDto.class);

                    productNamePriceSellerDto.setSeller(String.format("%s %s",
                            product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));

                    return productNamePriceSellerDto;
                })
                .collect(Collectors.toList());

    }
}
