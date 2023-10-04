package com.example.jsonexercise.models.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserNameAgeSoldProductsDto implements Serializable {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    @SerializedName("sold-products")
    private SoldProductsCountDto products;

    public UserNameAgeSoldProductsDto() {
        products = new SoldProductsCountDto();
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SoldProductsCountDto getProducts() {
        return products;
    }

    public void setProducts(SoldProductsCountDto products) {
        this.products = products;
    }
}
