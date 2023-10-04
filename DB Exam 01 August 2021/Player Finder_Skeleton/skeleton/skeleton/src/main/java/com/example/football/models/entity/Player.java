package com.example.football.models.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "players")
public class Player extends BaseEntity{
//    •	first name – accepts char sequences as values where their character length value higher than 2.
//•	last name – accepts char sequences as values where their character length value higher than 2.
//•	email – accepts valid email addresses (must contains '@' and '.' – a dot). The values are unique in the database.
//•	birth date – a date in the "dd/MM/yyyy" format.
//•	position – one of the following – ATT, MID, DEF.
//o	Note: The players table has relations with the towns, teams and stats tables.
    @Column(name ="first_name", nullable = false)
    private String firsName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "position", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Position position;
    @ManyToOne
    private Town town;
    @ManyToOne
    private Team team;
    @ManyToOne
    private Stat stat;

    public Player() {
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
