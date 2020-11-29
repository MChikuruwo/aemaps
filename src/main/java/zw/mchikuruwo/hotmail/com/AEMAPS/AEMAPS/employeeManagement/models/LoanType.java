package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "loan_type", schema = "a_e_m_a_p_s")
public class LoanType {
    private Integer id;
    private String type;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanType loanType = (LoanType) o;

        if (!Objects.equals(id, loanType.id)) return false;
        if (!Objects.equals(type, loanType.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
