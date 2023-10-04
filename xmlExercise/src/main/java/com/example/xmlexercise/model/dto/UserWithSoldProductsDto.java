package com.example.xmlexercise.model.dto;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsDto {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private Set<SoldProductWithBuyerDto> products;

    public UserWithSoldProductsDto() {
    }

    public UserWithSoldProductsDto(String firstName, String lastName, Set<SoldProductWithBuyerDto> products) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.products = products;
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
    public Set<SoldProductWithBuyerDto> getProducts() {
        return products;
    }
    public void setProducts(Set<SoldProductWithBuyerDto> products) {
        this.products = products;
    }
}
