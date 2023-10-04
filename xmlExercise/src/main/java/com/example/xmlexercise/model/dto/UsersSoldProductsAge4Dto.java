package com.example.xmlexercise.model.dto;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersSoldProductsAge4Dto {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute
    private int age;
    @XmlElement(name = "sold-products")
    private SoldProductsCount4Dto products;

    public UsersSoldProductsAge4Dto() {
        products = new SoldProductsCount4Dto();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SoldProductsCount4Dto getProducts() {
        return products;
    }

    public void setProducts(SoldProductsCount4Dto products) {
        this.products = products;
    }
}
