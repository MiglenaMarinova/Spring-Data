package softuni.exam.models.entity;

import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
//    •	price – accepts a positive number.
//•	published on – a date in the "dd/MM/yyyy" format.
//•	Constraint: The offers table has a relation with the apartments table.
//•	Constraint: The offers table has a relation with the agents table.
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "published_on", nullable = false)
    private LocalDate publishedOn;
    @ManyToOne
    private Apartment apartment;
    @ManyToOne
    private Agent agent;

    public Offer() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
