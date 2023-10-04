package softuni.exam.repository;

import org.hibernate.secure.spi.JaccPermissionDeclarations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Apartment;

import java.util.Optional;

// TODO:
@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    Optional<Apartment> findAllByAreaAndTown_TownName(Double area, String town_townName);

}
