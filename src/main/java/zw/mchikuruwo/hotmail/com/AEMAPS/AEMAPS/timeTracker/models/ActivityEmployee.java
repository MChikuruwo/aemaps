package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models;

import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "activity_employee", schema = "a_e_m_a_p_s")
@IdClass(ActivityEmployeeEntityPK.class)
public class ActivityEmployeeEntity {
    private Long activityId;
    private Long employeeId;
    private Activity activity;
    private Employee employee;

    @Id
    @Column(name = "activity_id")
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Id
    @Column(name = "employee_id")
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityEmployeeEntity that = (ActivityEmployeeEntity) o;
        return Objects.equals(activityId, that.activityId) && Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, employeeId);
    }

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activityByActivityId) {
        this.activity = activityByActivityId;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employeeByEmployeeId) {
        this.employee = employeeByEmployeeId;
    }
}
