package com.example.football.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatsSeedDto {
    @NotNull
    @Positive
    @XmlElement(name = "passing")
    private Float passing;
    @NotNull
    @Positive
    @XmlElement(name = "shooting")
    private Float shooting;
    @NotNull
    @Positive
    @XmlElement(name = "endurance")
    private Float endurance;

    public StatsSeedDto() {
    }

    public Float getPassing() {
        return passing;
    }

    public Float getShooting() {
        return shooting;
    }

    public Float getEndurance() {
        return endurance;
    }

    public void setPassing(Float passing) {
        this.passing = passing;
    }

    public void setShooting(Float shooting) {
        this.shooting = shooting;
    }

    public void setEndurance(Float endurance) {
        this.endurance = endurance;
    }
}
