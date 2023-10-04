package softuni.exam.models.entity;


import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "forecasts")
public class Forecast extends BaseEntity {
//•	day of week – enumerated value, one of the following – FRIDAY, SATURDAY, SUNDAY. Cannot be null.
//•	max temperature – a floating point number. Must be between -20 and 60 (both numbers are INCLUSIVE). Cannot be null.
//•	min temperature – a floating point number. Must be between -50 and 40 (both numbers are INCLUSIVE). Cannot be null.
//•	sunrise – time of the sunrise. Cannot be null.
//•	sunset – time of the sunset. Cannot be null.
//•	Constraint: The forecasts table has а relation with the cities table.

    private DayOfWeek dayOfWeek;
    private Double maxTemperature;
    private Double minTemperature;
    private LocalTime sunrise;
    private LocalTime sunset;

    private City city;

    public Forecast() {
    }
    @Column(name ="day_of_week", nullable = false)
    @Enumerated(EnumType.STRING)
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    @Column(name = "max_temperature", nullable = false)
    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
    @Column(name = "min_temperature", nullable = false)
    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }
    @Column(name = "sunrise", nullable = false)
    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }
    @Column(name = "sunset", nullable = false)
    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
