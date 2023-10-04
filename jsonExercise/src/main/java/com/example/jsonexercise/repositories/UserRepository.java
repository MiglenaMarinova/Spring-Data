package com.example.jsonexercise.repositories;

import com.example.jsonexercise.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "join u.soldProducts p " +
            "where p.buyer is not null " +
            "order by u.lastName, u.firstName" )
    List<User> findAllUsersWithSoldProductWithBuyerOrderByLastNameThenByFirstName();

    @Query("select u from User u " +
            "join u.soldProducts p " +
            "where p.buyer is not null " +
            "order by size(u.soldProducts) desc, u.lastName")
    Optional<List<User>> usersAndProducts();
}


