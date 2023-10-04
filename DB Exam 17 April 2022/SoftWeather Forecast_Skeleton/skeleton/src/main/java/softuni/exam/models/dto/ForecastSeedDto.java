package softuni.exam.models.dto;

import softuni.exam.models.entity.DayOfWeek;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastSeedDto {
    @XmlElement(name = "day_of_week")
    private DayOfWeek dayOfWeek;
    @XmlElement(name = "max_temperature")
    private Double maxTemperature;
    @XmlElement(name = "min_temperature")
    private Double minTemperature;
    @XmlElement(name = "sunrise")
    private String sunrise;
    @XmlElement(name = "sunset")
    private String sunset;
    @XmlElement(name = "city")
    private Long city;

    public ForecastSeedDto() {
    }

    public ForecastSeedDto(DayOfWeek dayOfWeek, Double maxTemperature, Double minTemperature, String sunrise, String sunset, Long city) {
        this.dayOfWeek = dayOfWeek;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.city = city;
    }

    @NotNull
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    @NotNull
    @DecimalMin("-20")
    @DecimalMax("60")
    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
    @NotNull
    @DecimalMin("-50")
    @DecimalMax("40")
    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }
    @NotNull
    @NotEmpty
    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }
    @NotNull
    @NotEmpty
    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
    @NotNull
    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }
}
