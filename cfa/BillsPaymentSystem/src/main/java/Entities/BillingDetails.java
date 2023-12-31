package Entities;

import javax.persistence.*;

@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetails extends BaseEntity{
    private String number;
    private BankUser owner;

    public BillingDetails() {
    }

    @Column(nullable = false)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne
    public BankUser getOwner() {
        return owner;
    }

    public void setOwner(BankUser owner) {
        this.owner = owner;
    }
}
