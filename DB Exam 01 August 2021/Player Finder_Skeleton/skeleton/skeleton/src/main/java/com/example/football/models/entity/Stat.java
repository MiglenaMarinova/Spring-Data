package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class Stat extends BaseEntity{
//    •	shooting – a floating point number. The value must be positive (larger than 0).
//     • passing – a floating point number. The value must be positive (larger than 0).
//    • endurance – a floating point number. The value must be positive (larger than 0).
    @Column(name = "shooting", nullable = false)
    private Float shooting;
    @Column(name = "passing", nullable = false)
    private Float passing;
    @Column(name = "endurance", nullable = false)
    private Float endurance;

    public Stat() {
    }

    public Float getShooting() {
        return shooting;
    }

    public void setShooting(Float shooting) {
        this.shooting = shooting;
    }

    public Float getPassing() {
        return passing;
    }

    public void setPassing(Float passing) {
        this.passing = passing;
    }

    public Float getEndurance() {
        return endurance;
    }

    public void setEndurance(Float endurance) {
        this.endurance = endurance;
    }
}
