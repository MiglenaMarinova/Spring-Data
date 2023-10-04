package softuni.exam.models.dto;

import java.math.BigDecimal;

public class ExportOffersDto {

    private String agentFirstName;
    private String agentLastName;
    private Long id;
    private Double area;
    private String townName;
    private BigDecimal price;


    public ExportOffersDto() {
    }

    public ExportOffersDto(String agentFirstName, String agentLastName,
                           Long id, Double area, String townName, BigDecimal price) {
        this.agentFirstName = agentFirstName;
        this.agentLastName = agentLastName;
        this.id = id;
        this.area = area;
        this.townName = townName;
        this.price = price;
    }

    public String getAgentFirstName() {
        return agentFirstName;
    }

    public void setAgentFirstName(String agentFirstName) {
        this.agentFirstName = agentFirstName;
    }

    public String getAgentLastName() {
        return agentLastName;
    }

    public void setAgentLastName(String agentLastName) {
        this.agentLastName = agentLastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
