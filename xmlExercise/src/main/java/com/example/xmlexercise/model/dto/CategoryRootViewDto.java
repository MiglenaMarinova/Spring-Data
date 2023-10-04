package com.example.xmlexercise.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryRootViewDto {
    @XmlElement(name ="category")
    private List<CategoryNameWithProductsDto> categories;

    public CategoryRootViewDto() {
    }

    public CategoryRootViewDto(List<CategoryNameWithProductsDto> categories) {
        this.categories = categories;
    }

    public List<CategoryNameWithProductsDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryNameWithProductsDto> categories) {
        this.categories = categories;
    }
}
