package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "job_title", schema = "a_e_m_a_p_s")
public class JobTitle {
    private Long id;
    private String titleName;
    private BusinessUnit businessUnit;
    private Department department;
    private Timestamp dateCreated;
    private Timestamp dateUpdated;

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
    @Column(name = "title_name", nullable = false, length = 255)
    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
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
        JobTitle jobTitle = (JobTitle) o;
        return Objects.equals(id, jobTitle.id) &&
                Objects.equals(titleName, jobTitle.titleName) &&
                Objects.equals(dateCreated, jobTitle.dateCreated) &&
                Objects.equals(dateUpdated, jobTitle.dateUpdated);
    }


    @ManyToOne
    @JoinColumn(name = "business_unit_id", referencedColumnName = "id", nullable = false)
    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
