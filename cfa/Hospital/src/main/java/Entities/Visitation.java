package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name= "visitations")
public class Visitation extends BaseEntity{
    private Date date;
    private String comments;
    private Patient patient;

    public Visitation() {
    }

    @Column(name = "date_time", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


@Column(nullable = false, columnDefinition = "TEXT")
    public String getComments() {
        return comments;
    }
    @Column(nullable = false, columnDefinition = "TEXT")
    public void setComments(String comments) {
        this.comments = comments;
    }
@ManyToOne
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
