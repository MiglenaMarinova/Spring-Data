package com.example.xmlexercise.model.dto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryNameWithProductsDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "products-count")
    private Long productCount;
    @XmlElement(name = "average-price")
    private Double averagePrice;
    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;

    public CategoryNameWithProductsDto() {
    }

    public CategoryNameWithProductsDto(String name, Long productCount, Double averagePrice, BigDecimal totalRevenue) {
        this.name = name;
        this.productCount = productCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
