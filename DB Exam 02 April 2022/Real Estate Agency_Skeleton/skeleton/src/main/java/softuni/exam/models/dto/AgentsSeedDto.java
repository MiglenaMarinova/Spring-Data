package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entity.Town;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AgentsSeedDto {

    //    •	first name – accepts char sequences as values where their character length value higher than or equal to 2.
//    The values are unique in the database.
//•	last name – accepts char sequences as values where their character length value higher than or equal to 2.
//•	email – an email – (must contains ‘@’ and ‘.’ – dot). The email of a seller is unique.
//•	Constraint: The agents table has а relation with the towns table.
    @Size(min = 2)
    @NotNull
    @NotEmpty
    @Expose
    private String firstName;
    @Size(min = 2)
    @NotNull
    @NotEmpty
    @Expose
    private String lastName;

    @NotNull
    @NotEmpty
    @Expose
    private String town;
    @Email
    @NotNull
    @Expose
    private String email;

    public AgentsSeedDto() {
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
