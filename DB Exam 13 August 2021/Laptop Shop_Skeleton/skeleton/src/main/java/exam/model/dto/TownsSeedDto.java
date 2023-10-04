package exam.model.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownsSeedDto {

    //    •	name – accepts char sequences as values where their character length value higher than or equal to 2.
//    The values are unique in the database.
//•	population – accepts number values (must be positive), 0 as a value is exclusive.
//•	travel guide – a long and detailed description of all known places with a character length value higher than or equal to 10.
    @Size(min = 2)
    @NotNull
    @NotEmpty
    @XmlElement(name = "name")
    private String name;
    @Positive
    @NotNull
    @XmlElement(name = "population")
    private Integer population;
    @Size(min = 10)
    @NotNull
    @NotEmpty
    @XmlElement(name = "travel-guide")
    private String travelGuide;

    public TownsSeedDto() {
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
