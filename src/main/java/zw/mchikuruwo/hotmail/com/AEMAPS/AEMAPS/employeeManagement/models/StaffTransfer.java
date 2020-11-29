package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "staff_transfer", schema = "a_e_m_a_p_s")
public class StaffTransfer {
    private Long id;
    private Date proposedStartDate;
    private String reason;
    private Boolean hasBeenApproved;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;
    private Employee releasingManager;
    private Department departmentToAssign;
    private Employee transferringEmployee;

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
    @Column(name = "proposed_start_date", nullable = false)
    public Date getProposedStartDate() {
        return proposedStartDate;
    }

    public void setProposedStartDate(Date proposedStartDate) {
        this.proposedStartDate = proposedStartDate;
    }

    @Basic
    @Column(name = "reason", nullable = false, length = -1)
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
        StaffTransfer that = (StaffTransfer) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(proposedStartDate, that.proposedStartDate) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(hasBeenApproved, that.hasBeenApproved) &&
                Objects.equals(dateCreated, that.dateCreated) &&
                Objects.equals(dateUpdated, that.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, proposedStartDate, reason, hasBeenApproved, dateCreated, dateUpdated);
    }

    @ManyToOne
    @JoinColumn(name = "releasing_manager", referencedColumnName = "id", nullable = false)
    public Employee getReleasingManager() {
        return releasingManager;
    }

    public void setReleasingManager(Employee releasingManager) {
        this.releasingManager = releasingManager;
    }

    @ManyToOne
    @JoinColumn(name = "department_to_assign", referencedColumnName = "id", nullable = false)
    public Department getDepartmentToAssign() {
        return departmentToAssign;
    }

    public void setDepartmentToAssign(Department departmentToAssign) {
        this.departmentToAssign = departmentToAssign;
    }

    @ManyToOne
    @JoinColumn(name = "transferring_employee", referencedColumnName = "id", nullable = false)
    public Employee getTransferringEmployee() {
        return transferringEmployee;
    }

    public void setTransferringEmployee(Employee transferringEmployee) {
        this.transferringEmployee = transferringEmployee;
    }
}
