package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.ExportOffersDto;
import softuni.exam.models.entity.Offer;

import java.util.List;

// TODO:
@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {


    @Query("SELECT new softuni.exam.models.dto.ExportOffersDto " +
            "(ag.firstName, ag.lastName, o.id, ap.area, t.townName, o.price) " +
            "FROM Offer o " +
            "JOIN Agent ag ON ag.id = o.agent.id " +
            "JOIN Apartment ap ON ap.id = o.apartment.id " +
            "JOIN Town t ON t.id = ap.town.id " +
            "WHERE o.apartment.apartmentType = 1 " +
            "ORDER BY ap.area DESC, o.price")
    List<ExportOffersDto> findOfferByApartmentType();



}
