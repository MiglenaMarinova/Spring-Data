package softuni.exam.instagraphlite.models.dto;

import com.google.gson.annotations.Expose;
import softuni.exam.instagraphlite.models.entity.Picture;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsersSeedDto {

    //    •	username – a char sequence. Cannot be null. The username is unique.
//    Must be between 2 and 18 (both numbers are INCLUSIVE)
//•	password – a char sequence. Cannot be null. Must be at least 4 characters long, inclusive.
//•	profilePicture – a Picture. Cannot be null.

    @Expose
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 18)
    private String username;
    @Expose
    @NotNull
    @NotEmpty
    @Size(min = 4)
    private String password;
    @Expose
    @NotNull
    private String profilePicture;

    public UsersSeedDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
