package exam.model.dto;

import exam.model.entity.Town;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopSeedDto {
    //    •	name – accepts char sequences as values where their character length value higher than or equal to 4.
//    The values are unique in the database.
//•	income – accepts number values that are more than or equal to 20000.
//•	address – accepts char sequences as values where their character length value higher than or equal to 4.
//•	employee count – accepts number values that are between 1 and 50
//o	(Larger than or equal to 1 and less than or equal to 50).
//•	shop area – accepts number values that are more than or equal to 150.
//•	Constraint: The shops table has a relation with the towns table.
    @Size(min = 4)
    @NotNull
    @NotEmpty
    @XmlElement(name = "address")
    private String address;
    @NotNull
    @Min(1)
    @Max(50)
    @XmlElement(name = "employee-count")
    private Integer employeeCount;
    @NotNull
    @DecimalMin("20000")
    @XmlElement(name = "income")
    private BigDecimal income;
    @Size(min = 4)
    @NotNull
    @NotEmpty
    @XmlElement(name = "name")
    private String name;
    @NotNull
    @Min(150)
    @XmlElement(name = "shop-area")
    private Integer shopArea;
    @NotNull
    @XmlElement(name = "town")
    private TownNameDto town;

    public ShopSeedDto() {
    }

    public ShopSeedDto(String address, Integer employeeCount,
                       BigDecimal income, String name, Integer shopArea, TownNameDto town) {
        this.address = address;
        this.employeeCount = employeeCount;
        this.income = income;
        this.name = name;
        this.shopArea = shopArea;
        this.town = town;
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

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShopArea() {
        return shopArea;
    }

    public void setShopArea(Integer shopArea) {
        this.shopArea = shopArea;
    }

    public TownNameDto getTown() {
        return town;
    }

    public void setTown(TownNameDto town) {
        this.town = town;
    }
}
