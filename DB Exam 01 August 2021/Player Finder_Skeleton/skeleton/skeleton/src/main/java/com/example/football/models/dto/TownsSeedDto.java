package com.example.football.models.dto;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownsSeedDto {

    //    •	name – accepts char sequences as values where their character length value higher than or equal to 2.
//    The values are unique in the database.
//•	population – accepts number values (must be a positive number), 0 as a value is exclusive.
//•	travel guide – a long and detailed description of all known places with a character
// length value higher than or equal to 10.
    @Size(min = 2)
    @NotNull
    @NotEmpty
    @Expose
    private String name;
    @NotNull
    @Positive
    @Expose
    private Integer population;
   @Size(min = 10)
   @NotNull
    @Expose
    private String travelGuide;

    public TownsSeedDto() {
    }

    public TownsSeedDto(String name, Integer population, String travelGuide) {
        this.name = name;
        this.population = population;
        this.travelGuide = travelGuide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
