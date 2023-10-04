package com.example.xmlexercise.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductViewRootDto {
    @XmlElement(name = "product")
    List<ProductWithoutBuyerDto> products;

    public List<ProductWithoutBuyerDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWithoutBuyerDto> products) {
        this.products = products;
    }
}
