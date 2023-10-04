package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountriesSeedDto {

    //    •	country name – accepts char sequence (between 2 to 60 inclusive). The values are unique in the database. Cannot be null.
//•	currency – accepts char sequences (between 2 and 20 inclusive). Cannot be null.
    @Expose
    private String countryName;
    @Expose
    private String currency;

    public CountriesSeedDto() {
    }

    public CountriesSeedDto(String countryName, String currency) {
        this.countryName = countryName;
        this.currency = currency;
    }
    @Size(min = 2, max = 60)
    @NotNull
    @NotEmpty
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    @Size(min = 2, max = 20)
    @NotEmpty
    @NotNull
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
