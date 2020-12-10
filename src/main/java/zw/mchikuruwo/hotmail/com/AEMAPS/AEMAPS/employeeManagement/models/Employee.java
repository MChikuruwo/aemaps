package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.enums.Gender;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.enums.ResidentialStatus;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models.Activity;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models.ActivityRequests;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "a_e_m_a_p_s")
public class Employee {
    private Long id;
    private Integer userId;
    private String employeeCode;
    private String name;
    private String surname;
    private Gender gender;
    private String mobileNumber;
    private ResidentialStatus residentialStatus;
    private String address1;
    private Double monthlySalary;
    private String accountNumber;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
    private EmployeeStatus employeeStatus;
    private JobTitle jobTitle;
    private List<Activity> activities;
    private List<ActivityRequests> activityRequests;


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
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "employee_code", nullable = false, length = 255)
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
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
    @Column(name = "surname", nullable = false, length = 255)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "mobile_number", nullable = false, length = 13)
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "residential_status", nullable = false)
    public ResidentialStatus getResidentialStatus() {
        return residentialStatus;
    }

    public void setResidentialStatus(ResidentialStatus residentialStatus) {
        this.residentialStatus = residentialStatus;
    }



    @Basic
    @Column(name = "address_1", nullable = false, length = -1)
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Basic
    @Column(name = "monthly_salary")
    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
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
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(userId, employee.userId) &&
                Objects.equals(employeeCode, employee.employeeCode) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(surname, employee.surname) &&
                Objects.equals(gender, employee.gender) &&
                Objects.equals(residentialStatus, employee.residentialStatus) &&
                Objects.equals(address1, employee.address1) &&
                Objects.equals(monthlySalary, employee.monthlySalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, employeeCode, name, surname, gender, residentialStatus, address1, monthlySalary);
    }

    @ManyToOne
    @JoinColumn(name = "employment_status_id", referencedColumnName = "id", nullable = false)
    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    @ManyToOne
    @JoinColumn(name = "job_title_id", referencedColumnName = "id", nullable = false)
    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Basic
    @Column(name = "account_number", nullable = true, length = 50)
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @ManyToMany
    @JoinColumn(name = "activities", referencedColumnName = "id")
    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
    public List<ActivityRequests> getActivityRequests() {
        return activityRequests;
    }

    public void setActivityRequests(List<ActivityRequests> activityRequests) {
        this.activityRequests = activityRequests;
    }
}
