package com.example.xmlexercise.model.dto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductNamePriceDto {
    @XmlAttribute(name = "name" )
    private String name;
    @XmlAttribute(name = "price")
    private BigDecimal price;

    public ProductNamePriceDto() {
    }

    public ProductNamePriceDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
