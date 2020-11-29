package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "salary_advance", schema = "a_e_m_a_p_s")
public class SalaryAdvance {
    private Long id;
    private Date dateApproved;
    private Timestamp dateCreated;
    private SalaryAdvanceApplication salaryAdvanceApplication;
    private Employee employee;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_approved", nullable = false)
    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalaryAdvance that = (SalaryAdvance) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dateApproved != null ? !dateApproved.equals(that.dateApproved) : that.dateApproved != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateApproved != null ? dateApproved.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "salary_advance_application_id", referencedColumnName = "id", nullable = false)
    public SalaryAdvanceApplication getSalaryAdvanceApplication() {
        return salaryAdvanceApplication;
    }

    public void setSalaryAdvanceApplication(SalaryAdvanceApplication salaryAdvanceApplication) {
        this.salaryAdvanceApplication = salaryAdvanceApplication;
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
