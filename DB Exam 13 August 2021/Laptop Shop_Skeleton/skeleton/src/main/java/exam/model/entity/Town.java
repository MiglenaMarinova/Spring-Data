package exam.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="towns")
public class Town extends BaseEntity{

//    •	name – accepts char sequences as values where their character length value higher than or equal to 2.
//    The values are unique in the database.
//•	population – accepts number values (must be positive), 0 as a value is exclusive.
//•	travel guide – a long and detailed description of all known places with a character length value higher than or equal to 10.

    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name ="population", nullable = false)
    private Integer population;
    @Column(name = "travel_guide", nullable = false, columnDefinition = "TEXT")
    private String travelGuide;

    public Town() {
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
