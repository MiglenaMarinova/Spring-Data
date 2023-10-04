package exam.model.dto;

import com.google.gson.annotations.Expose;
import exam.model.entity.Shop;
import exam.model.entity.WarrantyType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class ImportLaptopsDto {

    //    •	mac address – accepts char sequences as values where their character length value higher than 8.
//The values are unique in the database.
//•	cpu speed – accepts positive floating-point numbers.
//•	ram – accepts number values that are more than or equal to 8 and less than or equal to 128
//•	storage – accepts number values that are more than or equal to 128 and less than or equal to 1024
//•	description – a long and detailed description of all known places with a character length value higher than or equal to 10.
//•	price – accepts a positive number.
//•	warranty type – the enumeration, one of the following – BASIC, PREMIUM, LIFETIME.
//•	Constraint: The laptops table has a relation with the shops table.
    @NotNull
    @NotEmpty
    @Size(min = 8)
    @Expose
    private String macAddress;
    @NotNull
    @Positive
    @Expose
    private Double cpuSpeed;
    @NotNull
    @Min(8)
    @Max(128)
    @Expose
    private Integer ram;
    @NotNull
    @Min(128)
    @Max(1024)
    @Expose
    private Integer storage;
    @Size(min = 10)
    @NotNull
    @NotEmpty
    @Expose
    private String description;
    @NotNull
    @Positive
    @Expose
    private BigDecimal price;
    @NotNull
    @Expose
    private WarrantyType warrantyType;
    @NotNull
    @Expose
    private ShopNameDto shop;

    public ImportLaptopsDto() {
    }

    public ImportLaptopsDto(String macAddress, Double cpuSpeed, Integer ram,
                            Integer storage, String description, BigDecimal price,
                            WarrantyType warrantyType, ShopNameDto shop) {
        this.macAddress = macAddress;
        this.cpuSpeed = cpuSpeed;
        this.ram = ram;
        this.storage = storage;
        this.description = description;
        this.price = price;
        this.warrantyType = warrantyType;
        this.shop = shop;
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

    public ShopNameDto getShop() {
        return shop;
    }

    public void setShop(ShopNameDto shop) {
        this.shop = shop;
    }
}
