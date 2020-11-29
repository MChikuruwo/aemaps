package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "spouse_details", schema = "a_e_m_a_p_s")
public class SpouseDetails {
    private Long id;
    private String name;
    private String surname;
    private String nationalIdNumber;
    private String employer;
    private String employerAddress;
    private String mobileNumber;
    private String emailAddress;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
    private Employee employee;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "national_id_number", nullable = false, length = 100)
    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    public void setNationalIdNumber(String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }

    @Basic
    @Column(name = "employer", nullable = true, length = 255)
    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    @Basic
    @Column(name = "employer_address", nullable = true, length = -1)
    public String getEmployerAddress() {
        return employerAddress;
    }

    public void setEmployerAddress(String employerAddress) {
        this.employerAddress = employerAddress;
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
    @CreationTimestamp
    @Column(name = "date_created", nullable = false)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @UpdateTimestamp
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
        SpouseDetails that = (SpouseDetails) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(nationalIdNumber, that.nationalIdNumber) &&
                Objects.equals(employer, that.employer) &&
                Objects.equals(employerAddress, that.employerAddress) &&
                Objects.equals(mobileNumber, that.mobileNumber) &&
                Objects.equals(emailAddress, that.emailAddress) &&
                Objects.equals(dateCreated, that.dateCreated) &&
                Objects.equals(dateUpdated, that.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, nationalIdNumber, employer, employerAddress, mobileNumber, emailAddress, dateCreated, dateUpdated);
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employeeByEmployeeId) {
        this.employee = employeeByEmployeeId;
    }
}
