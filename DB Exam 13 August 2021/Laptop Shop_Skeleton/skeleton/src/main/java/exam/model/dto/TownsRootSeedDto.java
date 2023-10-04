package exam.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownsRootSeedDto {
    @XmlElement(name = "town")
    private List<TownsSeedDto> towns;

    public TownsRootSeedDto() {
    }

    public List<TownsSeedDto> getTowns() {
        return towns;
    }

    public void setTowns(List<TownsSeedDto> towns) {
        this.towns = towns;
    }
}
