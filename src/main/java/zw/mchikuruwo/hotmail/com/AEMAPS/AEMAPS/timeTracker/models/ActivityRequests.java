package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums.ActivityRequestAction;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums.ActivityRequestStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activity_requests", schema = "a_e_m_a_p_s")
public class ActivityRequests {
    private Long id;
    private LocalDateTime requestDate;
    private Employee employee;
    private Activity activity;
    private ActivityRequestAction activityRequestAction;
    private ActivityRequestStatus activityRequestStatus;

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
    @Column(name = "request_date")
    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime  requestDate) {
        this.requestDate = requestDate;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "activity_request_action")
    public ActivityRequestAction getActivityRequestAction() {
        return activityRequestAction;
    }

    public void setActivityRequestAction(ActivityRequestAction activityRequestAction) {
        this.activityRequestAction = activityRequestAction;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "activity_request_status")
    public ActivityRequestStatus getActivityRequestStatus() {
        return activityRequestStatus;
    }

    public void setActivityRequestStatus(ActivityRequestStatus activityRequestStatus) {
        this.activityRequestStatus = activityRequestStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityRequests that = (ActivityRequests) o;
        return Objects.equals(id, that.id) && Objects.equals(requestDate, that.requestDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestDate);
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
    @JoinColumn(name = "activity", referencedColumnName = "id", nullable = false)
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity  activityByActivity) {
        this.activity = activityByActivity;
    }

}
