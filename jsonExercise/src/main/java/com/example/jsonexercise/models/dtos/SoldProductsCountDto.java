package com.example.jsonexercise.models.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SoldProductsCountDto implements Serializable {
    @Expose
    private Integer count;
    @Expose
    @SerializedName("products")
    private List<ProductNameAndPriceDto> soldProducts;

    public SoldProductsCountDto() {

    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductNameAndPriceDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductNameAndPriceDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
