package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MembersSeedDto {

    //    •	first name - accepts char sequence (between 2 to 30 inclusive).
//•	last name - accepts char sequence (between 2 to 30 inclusive).
//•	address - accepts char sequence (between 2 to 40 inclusive). Can be nullable.
//•	phone number - accepts char sequence (between 2 to 20 inclusive). The values are unique in the database.
   @Expose
   @Size(min =2, max = 40)
    private String address;
    @Expose
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String firstName;
    @Expose
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String lastName;
    @Expose
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String phoneNumber;

    public MembersSeedDto() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
