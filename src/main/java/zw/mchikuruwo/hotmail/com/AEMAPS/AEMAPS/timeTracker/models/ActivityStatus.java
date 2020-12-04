package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "activity_status", schema = "a_e_m_a_p_s")
public class ActivityStatus {
    private Long id;
    private String status;

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
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityStatus that = (ActivityStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
