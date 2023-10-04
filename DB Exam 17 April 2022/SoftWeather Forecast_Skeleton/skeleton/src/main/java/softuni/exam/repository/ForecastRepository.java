package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.ExportForecastDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.DayOfWeek;
import softuni.exam.models.entity.Forecast;

import java.util.List;
import java.util.Optional;


@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

   Optional<Forecast> findAllByDayOfWeekAndCity(DayOfWeek dayOfWeek, City city);

   @Query("SELECT f.id, f.dayOfWeek, f.city.cityName, f.minTemperature, f.minTemperature, f.sunrise, f.sunset " +
           "FROM Forecast f " +
           "JOIN City c ON f.city.id = c.id " +
           "WHERE f.dayOfWeek = 'SUNDAY' AND c.population < 150000 " +
           "ORDER BY f.maxTemperature DESC, f.id")
   List<Forecast> findAllByDayOfWeekAndCitizen();

//      @Query("SELECT new softuni.exam.models.dto.ExportForecastDto " +
//      "(c.cityName, f.minTemperature, f.maxTemperature, f.sunrise, f.sunset) " +
//              "FROM Forecast f " +
//              "JOIN City c ON c.id = f.city.id " +
//              "WHERE c.population < 150000 AND f.dayOfWeek = 'SUNDAY' " +
//              "ORDER BY f.maxTemperature DESC, f.id")
//      List<ExportForecastDto> findAllByCityPopulationAndDayOfWeek();
}
