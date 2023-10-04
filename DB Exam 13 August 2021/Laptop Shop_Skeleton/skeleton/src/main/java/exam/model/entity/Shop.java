package exam.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "shops")
public class Shop extends BaseEntity{

//    •	name – accepts char sequences as values where their character length value higher than or equal to 4.
//    The values are unique in the database.
//•	income – accepts number values that are more than or equal to 20000.
//•	address – accepts char sequences as values where their character length value higher than or equal to 4.
//•	employee count – accepts number values that are between 1 and 50
//o	(Larger than or equal to 1 and less than or equal to 50).
//•	shop area – accepts number values that are more than or equal to 150.
//•	Constraint: The shops table has a relation with the towns table.
        @Column(name = "name", nullable = false, unique = true)
        private String name;
        @Column(name = "income", nullable = false)
        private BigDecimal income;
        @Column(name = "address", nullable = false)
        private String address;
        @Column(name = "employee_count", nullable = false)
        private Integer employeeCount;
        @Column(name = "shop_area", nullable = false)
        private Integer shopArea;
        @ManyToOne
        private Town town;

    public Shop() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public Integer getShopArea() {
        return shopArea;
    }

    public void setShopArea(Integer shopArea) {
        this.shopArea = shopArea;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
