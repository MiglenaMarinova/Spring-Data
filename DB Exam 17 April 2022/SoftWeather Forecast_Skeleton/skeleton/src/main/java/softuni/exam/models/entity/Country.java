package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="countries")
public class Country extends BaseEntity{

//    •	country name – accepts char sequence (between 2 to 60 inclusive). The values are unique in the database. Cannot be null.
//•	currency – accepts char sequences (between 2 and 20 inclusive). Cannot be null.

    private String countryName;
    private String currency;

    public Country() {
    }
    @Column(name = "country_name", nullable = false, unique = true)
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String name) {
        this.countryName = name;
    }
    @Column(name = "currency", nullable = false)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
