package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "library_members")
public class LibraryMember extends BaseEntity{

//    •	first name - accepts char sequence (between 2 to 30 inclusive).
//•	last name - accepts char sequence (between 2 to 30 inclusive).
//•	address - accepts char sequence (between 2 to 40 inclusive). Can be nullable.
//•	phone number - accepts char sequence (between 2 to 20 inclusive). The values are unique in the database.
    @Column(name = "first_name", nullable = false)
    private String firstName;
   @Column(name = "last_name", nullable = false)
    private String lastName;
   @Column(name = "address")
    private String address;
   @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    public LibraryMember() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
