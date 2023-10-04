package com.example.xmlexercise.model.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersProductsView4Dto {
    @XmlAttribute(name = "count")
    private Integer usersCount;
    @XmlElement(name = "user")
    private List<UsersSoldProductsAge4Dto> users;

    public UsersProductsView4Dto() {
    }

    public UsersProductsView4Dto(List<UsersSoldProductsAge4Dto> users) {
        this.users = users;
        this.usersCount = users.size();
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<UsersSoldProductsAge4Dto> getUsers() {
        return users;
    }

    public void setUsers(List<UsersSoldProductsAge4Dto> users) {
        this.users = users;
    }


}
