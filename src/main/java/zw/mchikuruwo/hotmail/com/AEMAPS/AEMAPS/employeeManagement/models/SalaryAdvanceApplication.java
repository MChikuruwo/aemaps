package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "salary_advance_application", schema = "a_e_m_a_p_s")
public class SalaryAdvanceApplication {
    private Long id;
    private BigDecimal amountRequired;
    private Boolean hasBeenApproved;
    private String accountNumber;
    private String purpose;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
    private Employee employee;
    private Employee receivingManager;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "amount_required", nullable = false, precision = 2)
    public BigDecimal getAmountRequired() {
        return amountRequired;
    }

    public void setAmountRequired(BigDecimal amountRequired) {
        this.amountRequired = amountRequired;
    }

    @Basic
    @Column(name = "has_been_approved", nullable = false)
    public Boolean getHasBeenApproved() {
        return hasBeenApproved;
    }

    public void setHasBeenApproved(Boolean hasBeenApproved) {
        this.hasBeenApproved = hasBeenApproved;
    }

    @Basic
    @Column(name = "purpose", nullable = false, length = -1)
    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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

        SalaryAdvanceApplication that = (SalaryAdvanceApplication) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (amountRequired != null ? !amountRequired.equals(that.amountRequired) : that.amountRequired != null)
            return false;
        if (hasBeenApproved != null ? !hasBeenApproved.equals(that.hasBeenApproved) : that.hasBeenApproved != null)
            return false;
        if (accountNumber != null ? !accountNumber.equals(that.accountNumber) : that.accountNumber != null)
            return false;
        if (purpose != null ? !purpose.equals(that.purpose) : that.purpose != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (dateUpdated != null ? !dateUpdated.equals(that.dateUpdated) : that.dateUpdated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (amountRequired != null ? amountRequired.hashCode() : 0);
        result = 31 * result + (hasBeenApproved != null ? hasBeenApproved.hashCode() : 0);
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        result = 31 * result + (purpose != null ? purpose.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateUpdated != null ? dateUpdated.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employeeByEmployeeId) {
        this.employee = employeeByEmployeeId;
    }

    @ManyToOne
    @JoinColumn(name = "receiving_manager", referencedColumnName = "id", nullable = false)
    public Employee getReceivingManager() {
        return receivingManager;
    }

    public void setReceivingManager(Employee employeeByReceivingManager) {
        this.receivingManager = employeeByReceivingManager;
    }
}
