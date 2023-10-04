package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity{
//• name – accepts char sequences as values where their character length value higher than or equal to 3.
// The values are unique in the database.
//•	stadium name – accepts char sequences as values where their character length value higher than or equal to 3.
//•	fan base – accepts number values that are more than or equal to 1000.
//•	history – a long and detailed description of team's history with a character
// length value higher than or equal to 10.
//o	Note: The teams table has relation with the towns table.
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "stadium_name", nullable = false)
    private String stadiumName;
    @Column(name = "fan_base", nullable = false)
    private Integer fanBase;
    @Column(name = "history", nullable = false, columnDefinition = "TEXT")
    private String history;
    @ManyToOne
    private Town town;

    public Team() {
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

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
