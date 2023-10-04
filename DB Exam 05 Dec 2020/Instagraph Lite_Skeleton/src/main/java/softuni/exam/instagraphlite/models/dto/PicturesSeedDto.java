package softuni.exam.instagraphlite.models.dto;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PicturesSeedDto {

    //    •	path – a char sequence. Cannot be null. The path is unique.
//•	size – a floating point number. Cannot be null. Must be between 500 and 60000 (both numbers are INCLUSIVE)
    @Expose
    @NotNull
    @NotEmpty
    private String path;
    @Expose
    @NotNull
    @Min(500)
    @Max(60000)
    private Double size;

    public PicturesSeedDto() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
