package Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity {
//     first name, last name, address, email, date of birth, picture, information on whether
//he has medical insurance or not.

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Date dateOfBirth;
    private String picturePath;
    private Boolean hasMedInsurance;
    private Set<Visitation> visitations;
    private Set<Medicament> medicaments;
    private Set<Diagnose> diagnoses;

    public Patient() {
        this.visitations = new HashSet<>();
        this.medicaments = new HashSet<>();
        this.diagnoses = new HashSet<>();
    }

    @Column(name = "first_name", nullable = false, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(nullable = false, length = 70)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "date_of_birth", nullable = false)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "name")
    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Column(name = "has_medical_insurance", nullable = false)
    public Boolean getHasMedInsurance() {
        return hasMedInsurance;
    }

    public void setHasMedInsurance(Boolean hasMedInsurance) {
        this.hasMedInsurance = hasMedInsurance;
    }

    @OneToMany(mappedBy = "patient", cascade = CascadeType.PERSIST)
    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "patients_medicaments",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "patients_diagnoses",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"))
    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }
}
