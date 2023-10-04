package exam.repository;

import exam.model.dto.ExportLaptopDto;
import exam.model.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//ToDo:
@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    Optional<Laptop> findAllByMacAddress(String macAddress);


    @Query("SELECT new exam.model.dto.ExportLaptopDto (" +
            "l.macAddress, l.cpuSpeed, l.ram, l.storage, l.price, s.name, t.name) " +
            "FROM Laptop l " +
            "JOIN Shop s ON s.id = l.shop.id " +
            "JOIN Town t ON t.id =s.town.id " +
            "ORDER BY l.cpuSpeed DESC, l.ram DESC, l.storage DESC, l.macAddress")
    List<ExportLaptopDto> exportBestLaptops();
}
