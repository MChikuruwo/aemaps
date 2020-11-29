package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "loan", schema = "a_e_m_a_p_s")
public class Loan {
    private Long id;
    private Date dateApproved;
    private Timestamp dateCreated;
    private LoanApplication loanApplication;
    private Employee employee;

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
    @Column(name = "date_approved")
    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        if (!Objects.equals(id, loan.id)) return false;
        if (!Objects.equals(dateApproved, loan.dateApproved)) return false;
        if (!Objects.equals(dateCreated, loan.dateCreated)) return false;

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
    @JoinColumn(name = "loan_application_id", referencedColumnName = "id", nullable = false)
    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplicationByLoanApplicationId) {
        this.loanApplication = loanApplicationByLoanApplicationId;
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
