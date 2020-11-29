package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "next_of_kin", schema = "a_e_m_a_p_s")
public class NextOfKin {
    private Long id;
    private String name;
    private String surname;
    private String relationshipToApplicant;
    private String address;
    private String mobileNumber;
    private String emailAddress;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
    private Employee employee;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = -1)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "relationship_to_applicant", nullable = false, length = 255)
    public String getRelationshipToApplicant() {
        return relationshipToApplicant;
    }

    public void setRelationshipToApplicant(String relationshipToApplicant) {
        this.relationshipToApplicant = relationshipToApplicant;
    }

    @Basic
    @Column(name = "address", nullable = false, length = -1)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "mobile_number", nullable = false, length = 30)
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Basic
    @Column(name = "email_address", nullable = true, length = 100)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Basic
    @Column(name = "date_created", nullable = false)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "date_updated", nullable = false)
    public Timestamp getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NextOfKin nextOfKin = (NextOfKin) o;
        return Objects.equals(id, nextOfKin.id) &&
                Objects.equals(name, nextOfKin.name) &&
                Objects.equals(surname, nextOfKin.surname) &&
                Objects.equals(relationshipToApplicant, nextOfKin.relationshipToApplicant) &&
                Objects.equals(address, nextOfKin.address) &&
                Objects.equals(mobileNumber, nextOfKin.mobileNumber) &&
                Objects.equals(emailAddress, nextOfKin.emailAddress) &&
                Objects.equals(dateCreated, nextOfKin.dateCreated) &&
                Objects.equals(dateUpdated, nextOfKin.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, relationshipToApplicant, address, mobileNumber, emailAddress, dateCreated, dateUpdated);
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
