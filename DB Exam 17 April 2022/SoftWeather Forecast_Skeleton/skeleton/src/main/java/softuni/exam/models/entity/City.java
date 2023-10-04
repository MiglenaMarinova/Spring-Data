package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City extends BaseEntity{

//    •	city name – a char sequence (between 2 to 60 inclusive). The values are unique in the database. Cannot be null.
//•	description – accepts very long char sequence (min 2 symbols).
//•	population – accepts number values that are more than or equal to 500. Cannot be null.
//•	Constraint: The cities table has а relation with the countries table.

    private String cityName;
    private String description;
    private Integer population;
    private Country country;

    public City() {
    }
    @Column(name = "city_name", nullable = false, unique = true)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    @Column(name ="description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "population", nullable = false)
    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
    @ManyToOne
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
