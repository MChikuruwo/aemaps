package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "activity_importance", schema = "a_e_m_a_p_s")
public class ActivityImportance {
    private Long id;
    private String importance;

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
    @Column(name = "importance")
    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityImportance that = (ActivityImportance) o;
        return Objects.equals(id, that.id) && Objects.equals(importance, that.importance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, importance);
    }
}
