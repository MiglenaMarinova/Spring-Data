package exam.model.dto;

import com.google.gson.annotations.Expose;
import exam.model.entity.Town;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ImportCustomersDto {

    //    •	first name – accepts char sequences as values where their character length value higher than or equal to 2.
//   •	last name – accepts char sequences as values where their character length value higher than or equal to 2.
//   •	email  – accepts valid email addresses (must contains '@' and '.' – a dot). The values are unique in the database.
//•	registered on – a date when а customer registers in the shop.
//•	Constraint: The customers table has а relation with the towns table.
    @Size(min =2)
    @NotNull
    @NotEmpty
    @Expose
    private String firstName;
    @Size(min =2)
    @NotNull
    @NotEmpty
    @Expose
    private String lastName;
    @Email
    @NotNull
    @NotEmpty
    @Expose
    private String email;
    @NotNull
    @NotEmpty
    @Expose
    private String registeredOn;
    @Expose
    private TownNameDto town;

    public ImportCustomersDto() {
    }

    public ImportCustomersDto(String firstName, String lastName, String email, String registeredOn, TownNameDto town) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registeredOn = registeredOn;
        this.town = town;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }

    public TownNameDto getTown() {
        return town;
    }

    public void setTown(TownNameDto town) {
        this.town = town;
    }
}
