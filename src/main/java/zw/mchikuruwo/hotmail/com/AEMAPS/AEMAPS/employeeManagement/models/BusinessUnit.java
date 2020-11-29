package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "business_unit", schema = "a_e_m_a_p_s")
public class BusinessUnit {
    private Integer id;
    private String businessUnit;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "business_unit", nullable = false, length = 255)
    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessUnit that = (BusinessUnit) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(businessUnit, that.businessUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, businessUnit);
    }
}
