package softuni.exam.models.dto;

import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDate;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSeedDto {
    @NotNull
    @Positive
    @XmlElement(name = "price")
    private BigDecimal price;
    @XmlElement(name = "agent")
    private AgentNameDto agent;
    @XmlElement(name = "apartment")
    private ApartmentIdDto apartment;
    @NotNull
    @XmlElement(name = "publishedOn")
    private String publishedOn;

    public OfferSeedDto() {
    }

    public OfferSeedDto(BigDecimal price, AgentNameDto agent,
                        ApartmentIdDto apartment, String publishedOn) {
        this.price = price;
        this.agent = agent;
        this.apartment = apartment;
        this.publishedOn = publishedOn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public AgentNameDto getAgent() {
        return agent;
    }

    public void setAgent(AgentNameDto agent) {
        this.agent = agent;
    }

    public ApartmentIdDto getApartment() {
        return apartment;
    }

    public void setApartment(ApartmentIdDto apartment) {
        this.apartment = apartment;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }
}
