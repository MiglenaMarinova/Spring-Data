package com.example.jsonexercise.models.dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class UsersAndProductsDto implements Serializable {
    @Expose
    private Integer userCount;
    @Expose
    private List<UserNameAgeSoldProductsDto> users;

    public UsersAndProductsDto() {
    }

    public UsersAndProductsDto(List<UserNameAgeSoldProductsDto> users) {
        this.users = users;
        this.userCount = users.size();
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public List<UserNameAgeSoldProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserNameAgeSoldProductsDto> users) {
        this.users = users;
    }
}
