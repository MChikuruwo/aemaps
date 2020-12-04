package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models;

import org.hibernate.annotations.UpdateTimestamp;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "activity_requests", schema = "a_e_m_a_p_s")
public class ActivityRequests {
    private Long id;
    private Timestamp requestDate;
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
    @UpdateTimestamp
    @Column(name = "request_date")
    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
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

    @ManyToOne
    @JoinColumn(name = "activity_request_action", referencedColumnName = "id", nullable = false)
    public ActivityRequestAction getActivityRequestAction() {
        return activityRequestAction;
    }

    public void setActivityRequestAction(ActivityRequestAction activityRequestActionByActivityRequestAction) {
        this.activityRequestAction = activityRequestActionByActivityRequestAction;
    }

    @ManyToOne
    @JoinColumn(name = "activity_request_status", referencedColumnName = "id", nullable = false)
    public ActivityRequestStatus getActivityRequestStatus() {
        return activityRequestStatus;
    }

    public void setActivityRequestStatus(ActivityRequestStatus activityRequestStatusByActivityRequestStatus) {
        this.activityRequestStatus = activityRequestStatusByActivityRequestStatus;
    }
}
