package com.example.automappingdto.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends  BaseEntity{
    private User buyer;
    private List<Game> buyerGames;

    public Order() {
    }
@ManyToOne
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
@ManyToMany
    public List<Game> getBuyerGames() {
        return buyerGames;
    }

    public void setBuyerGames(List<Game> buyerGames) {
        this.buyerGames = buyerGames;
    }
}
