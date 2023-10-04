package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "apartments")
public class Apartment extends BaseEntity{
//    •	apartment type – the enumeration, one of the following – two_rooms, three_rooms, four_rooms
//•	area – accepts number values that are more than or equal to 40.00.
//•	Constraint: The apartment table has а relation with the towns table.
    @Column(name = "apartment_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ApartmentType apartmentType;
    @Column(name = "area", nullable = false)
    private Double area;
    @ManyToOne
    private Town town;

    public Apartment() {
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

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
