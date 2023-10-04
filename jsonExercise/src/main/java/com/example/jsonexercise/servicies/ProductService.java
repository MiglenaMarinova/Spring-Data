package com.example.jsonexercise.servicies;

import com.example.jsonexercise.models.dtos.ProductNamePriceSellerDto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts() throws IOException;

    List<ProductNamePriceSellerDto> findAllProductsInRange(BigDecimal lower, BigDecimal upper);
}
