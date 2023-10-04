package com.example.football.models.dto;

import com.example.football.models.entity.Town;
import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TeamsSeedDto {

    //• name – accepts char sequences as values where their character length value higher than or equal to 3.
// The values are unique in the database.
//•	stadium name – accepts char sequences as values where their character length value higher than or equal to 3.
//•	fan base – accepts number values that are more than or equal to 1000.
//•	history – a long and detailed description of team's history with a character
// length value higher than or equal to 10.
//o	Note: The teams table has relation with the towns table.

    @NotNull
    @Size(min = 3)
    @NotEmpty
    @Expose
    private String name;
    @NotNull
    @Size(min = 3)
    @NotEmpty
    @Expose
    private String stadiumName;
    @NotNull
    @Min(1000)
    @Expose
    private Integer fanBase;
    @NotNull
    @NotEmpty
    @Size(min = 10)
    @Expose
    private String history;
    @NotNull
    @Expose
    private String townName;

    public TeamsSeedDto() {
    }

    public TeamsSeedDto(String name, String stadiumName, Integer fanBase, String history, String townName) {
        this.name = name;
        this.stadiumName = stadiumName;
        this.fanBase = fanBase;
        this.history = history;
        this.townName = townName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public Integer getFanBase() {
        return fanBase;
    }

    public void setFanBase(Integer fanBase) {
        this.fanBase = fanBase;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
