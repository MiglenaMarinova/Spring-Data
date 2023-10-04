package com.example.xmlexercise.model.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsCount4Dto {
    @XmlAttribute(name = "count")
    private Integer count;
    @XmlElement(name = "product")
    private List<ProductNamePriceDto> soldProducts;

    public SoldProductsCount4Dto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductNamePriceDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductNamePriceDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
