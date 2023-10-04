package com.example.jsonexercise.models.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class UserSoldDto implements Serializable {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Set<ProductWithBuyerDto> soldProducts;

    public UserSoldDto() {
    }

    public UserSoldDto(String firstName, String lastName, Set<ProductWithBuyerDto> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.soldProducts = soldProducts;
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

    public Set<ProductWithBuyerDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductWithBuyerDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
