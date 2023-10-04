package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayersRootSeedDto {
    @XmlElement(name = "player")
    List<PlayersSeedDto> players;

    public PlayersRootSeedDto() {
    }

    public PlayersRootSeedDto(List<PlayersSeedDto> players) {
        this.players = players;
    }

    public List<PlayersSeedDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayersSeedDto> players) {
        this.players = players;
    }
}
