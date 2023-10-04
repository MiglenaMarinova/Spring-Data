package exam.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "laptops")
public class Laptop extends BaseEntity{

//    •	mac address – accepts char sequences as values where their character length value higher than 8.
//The values are unique in the database.
//•	cpu speed – accepts positive floating-point numbers.
//•	ram – accepts number values that are more than or equal to 8 and less than or equal to 128
//•	storage – accepts number values that are more than or equal to 128 and less than or equal to 1024
//•	description – a long and detailed description of all known places with a character length value higher than or equal to 10.
//•	price – accepts a positive number.
//•	warranty type – the enumeration, one of the following – BASIC, PREMIUM, LIFETIME.
//•	Constraint: The laptops table has a relation with the shops table.
    @Column(name = "mac_address", nullable = false, unique = true)
    private String macAddress;
    @Column(name = "cpu_speed", nullable = false)
    private Double cpuSpeed;
    @Column(name ="ram", nullable = false)
    private Integer ram;
    @Column(name = "storage", nullable = false)
    private Integer storage;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "warranty_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private WarrantyType warrantyType;
    @ManyToOne
    private Shop shop;

    public Laptop() {
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(Double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
