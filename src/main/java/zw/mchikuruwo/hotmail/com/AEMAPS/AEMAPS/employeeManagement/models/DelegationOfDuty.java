package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "delegation_of_duty", schema = "a_e_m_a_p_s")
public class DelegationOfDuty {
    private Long id;
    private String duty;
    private Date fromDate;
    private Date toDate;
    private String reason;
    private Timestamp dateCreated;
    private Employee assigningManager;
    private Employee assignedEmployee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "duty")
    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Basic
    @Column(name = "from_date")
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Basic
    @Column(name = "to_date")
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

        DelegationOfDuty that = (DelegationOfDuty) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (duty != null ? !duty.equals(that.duty) : that.duty != null) return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        if (toDate != null ? !toDate.equals(that.toDate) : that.toDate != null) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (duty != null ? duty.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    public Employee getAssigningManager() {
        return assigningManager;
    }

    public void setAssigningManager(Employee employeeByAssigningManager) {
        this.assigningManager = employeeByAssigningManager;
    }

    @ManyToOne
    @JoinColumn(name = "assign_to", referencedColumnName = "id", nullable = false)
    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee employeeByAssignedEmployee) {
        this.assignedEmployee = employeeByAssignedEmployee;
    }
}
