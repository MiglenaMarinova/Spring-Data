package com.example.football.models.dto;

import com.example.football.models.entity.Position;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD )
public class PlayersSeedDto {

    //    •	first name – accepts char sequences as values where their character length value higher than 2.
//•	last name – accepts char sequences as values where their character length value higher than 2.
//•	email – accepts valid email addresses (must contains '@' and '.' – a dot). The values are unique in the database.
//•	birth date – a date in the "dd/MM/yyyy" format.
//•	position – one of the following – ATT, MID, DEF.
//o	Note: The players table has relations with the towns, teams and stats tables.
    @Size(min = 2)
    @NotNull
    @NotEmpty
    @XmlElement(name = "first-name")
    private String firsName;
    @Size(min = 2)
    @NotNull
    @NotEmpty
    @XmlElement(name = "last-name")
    private String lastName;
    @Email
    @NotEmpty
    @NotNull
    @XmlElement(name = "email")
    private String email;
    @NotNull
    @NotEmpty
    @XmlElement(name = "birth-date")
    private String birthDate;
    @NotNull
    @XmlElement(name = "position")
    private Position position;
    @NotNull
    @XmlElement(name = "town")
    private TownNameDto town;
    @NotNull
    @XmlElement(name = "team")
    private TeamNameDto team;
    @NotNull
    @XmlElement(name = "stat")
    private StatIdDto stat;

    public PlayersSeedDto() {
    }

    public PlayersSeedDto(String firsName, String lastName, String email, String birthDate,
                          Position position, TownNameDto town, TeamNameDto team, StatIdDto stat) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.position = position;
        this.town = town;
        this.team = team;
        this.stat = stat;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TownNameDto getTown() {
        return town;
    }

    public void setTown(TownNameDto town) {
        this.town = town;
    }

    public TeamNameDto getTeam() {
        return team;
    }

    public void setTeam(TeamNameDto team) {
        this.team = team;
    }

    public StatIdDto getStat() {
        return stat;
    }

    public void setStat(StatIdDto stat) {
        this.stat = stat;
    }
}
