package com.example.automappingdto.models.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
//    email, password, full name, list
//of games and information on whether he/she is an administrator or not

    private String email;
    private String password;
    private String fullName;
    private List<Game> usersGames;
    private boolean isAdmin;

    public User() {
    }
    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @ManyToMany
    public List<Game> getUsersGames() {
        return usersGames;
    }

    public void setUsersGames(List<Game> usersGames) {
        this.usersGames = usersGames;
    }
    @Column(name = "is_admin")
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


}
