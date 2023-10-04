package softuni.exam.instagraphlite.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity{
//    •	path – a char sequence. Cannot be null. The path is unique.
//•	size – a floating point number. Cannot be null. Must be between 500 and 60000 (both numbers are INCLUSIVE)
    @Column(name = "path", nullable = false, unique = true)
    private String path;
    @Column(name ="size", nullable = false)
    private Double size;

    public Picture() {
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
