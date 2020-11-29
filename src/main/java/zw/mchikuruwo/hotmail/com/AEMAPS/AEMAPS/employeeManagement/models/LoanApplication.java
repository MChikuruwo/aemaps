package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "loan_application", schema = "a_e_m_a_p_s")
public class LoanApplication {
    private Long id;
    private BigDecimal amountRequired;
    private String repaymentPeriod;
    private String loanPurpose;
    private Boolean hasBeenApproved;
    private Date contractExpiryDate;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
    private Employee employee;
    private LoanType loanType;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "amount_required")
    public BigDecimal getAmountRequired() {
        return amountRequired;
    }

    public void setAmountRequired(BigDecimal amountRequired) {
        this.amountRequired = amountRequired;
    }

    @Basic
    @Column(name = "repayment_period")
    public String getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setRepaymentPeriod(String repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    @Basic
    @Column(name = "loan_purpose")
    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    @Basic
    @Column(name = "has_been_approved")
    public Boolean getHasBeenApproved() {
        return hasBeenApproved;
    }

    public void setHasBeenApproved(Boolean hasBeenApproved) {
        this.hasBeenApproved = hasBeenApproved;
    }

    @Basic
    @Column(name = "contract_expiry_date")
    public Date getContractExpiryDate() {
        return contractExpiryDate;
    }

    public void setContractExpiryDate(Date contractExpiryDate) {
        this.contractExpiryDate = contractExpiryDate;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "date_created")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @UpdateTimestamp
    @Column(name = "date_updated")
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

        LoanApplication that = (LoanApplication) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (amountRequired != null ? !amountRequired.equals(that.amountRequired) : that.amountRequired != null)
            return false;
        if (repaymentPeriod != null ? !repaymentPeriod.equals(that.repaymentPeriod) : that.repaymentPeriod != null)
            return false;
        if (loanPurpose != null ? !loanPurpose.equals(that.loanPurpose) : that.loanPurpose != null) return false;
        if (hasBeenApproved != null ? !hasBeenApproved.equals(that.hasBeenApproved) : that.hasBeenApproved != null)
            return false;
        if (contractExpiryDate != null ? !contractExpiryDate.equals(that.contractExpiryDate) : that.contractExpiryDate != null)
            return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (dateUpdated != null ? !dateUpdated.equals(that.dateUpdated) : that.dateUpdated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (amountRequired != null ? amountRequired.hashCode() : 0);
        result = 31 * result + (repaymentPeriod != null ? repaymentPeriod.hashCode() : 0);
        result = 31 * result + (loanPurpose != null ? loanPurpose.hashCode() : 0);
        result = 31 * result + (hasBeenApproved != null ? hasBeenApproved.hashCode() : 0);
        result = 31 * result + (contractExpiryDate != null ? contractExpiryDate.hashCode() : 0);
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
    @JoinColumn(name = "loan_type_id", referencedColumnName = "id", nullable = false)
    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanTypeByLoanTypeId) {
        this.loanType = loanTypeByLoanTypeId;
    }
}
