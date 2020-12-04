package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "activity_request_action", schema = "a_e_m_a_p_s")
public class ActivityRequestAction {
    private Long id;
    private String action;

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
    @Column(name = "action")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityRequestAction that = (ActivityRequestAction) o;
        return Objects.equals(id, that.id) && Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, action);
    }
}
