package exam.model.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ShopNameDto {
    @NotNull
    @NotEmpty
    @Expose
    private String name;

    public ShopNameDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
