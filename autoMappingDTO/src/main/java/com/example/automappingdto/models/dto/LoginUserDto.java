package com.example.automappingdto.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class LoginUserDto {
    private String email;
    private String password;

    public LoginUserDto() {
    }

    public LoginUserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
    @Email(message = " Enter valid email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}$", message = "Enter a valid password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
