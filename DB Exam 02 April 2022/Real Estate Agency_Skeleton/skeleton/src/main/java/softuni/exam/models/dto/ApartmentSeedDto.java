package softuni.exam.models.dto;

import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Town;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentSeedDto {

    //    •	apartment type – the enumeration, one of the following – two_rooms, three_rooms, four_rooms
//•	area – accepts number values that are more than or equal to 40.00.
//•	Constraint: The apartment table has а relation with the towns table.
    @NotNull
    @XmlElement(name = "apartmentType")
    private ApartmentType apartmentType;
    @NotNull
    @DecimalMin("40.00")
    @XmlElement(name = "area")
    private Double area;
    @NotNull
    @XmlElement(name = "town")
    private String town;

    public ApartmentSeedDto() {
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
