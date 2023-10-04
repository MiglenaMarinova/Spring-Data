package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownsSeedDto {
    //    •	town name – accepts char sequences as values where their character length value is higher than or equal to 2.
//    The values are unique in the database.
//•	population – accepts number values (must be positive), 0 as a value is exclusive.
    @Size(min = 2)
    @NotNull
    @NotEmpty
    @Expose
    private String townName;
    @Positive
    @Expose
    private Integer population;

    public TownsSeedDto() {
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
