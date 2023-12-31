package com.example.football.models.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = " team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamNameDto {
    @NotNull
    @XmlElement(name = "name")
    private String name;

    public TeamNameDto() {
    }

    public TeamNameDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
