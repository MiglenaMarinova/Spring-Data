package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatRootSeedDto {
    @XmlElement(name = "stat")
    List<StatsSeedDto> stats;

    public StatRootSeedDto() {
    }

    public List<StatsSeedDto> getStats() {
        return stats;
    }

    public void setStats(List<StatsSeedDto> stats) {
        this.stats = stats;
    }
}
