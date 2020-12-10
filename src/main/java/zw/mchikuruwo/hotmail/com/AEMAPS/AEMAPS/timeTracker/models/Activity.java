package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.converter.LocalDateTimeConverter;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums.ActivityImportance;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums.ActivityStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activity", schema = "a_e_m_a_p_s")
public class Activity {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;
    private Timestamp dateUpdated;
    private Set<Employee> employee;
    private ActivityImportance activityImportance;
    private ActivityStatus activityStatus;
    private Set<ActivityRequests> activityRequests;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "start_time")
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "end_time")
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "duration")
    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "activity_importance")
    public ActivityImportance getActivityImportance() {
        return activityImportance;
    }

    public void setActivityImportance(ActivityImportance activityImportance) {
        this.activityImportance = activityImportance;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "activity_status")
    public ActivityStatus getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatus activityStatus) {
        this.activityStatus = activityStatus;
    }

    @Basic
    @UpdateTimestamp
    @Column(name = "date_updated")
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
        Activity that = (Activity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(dateUpdated, that.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, startTime, endTime, dateUpdated);
    }

    @ManyToMany
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }

    @ManyToMany
    @JoinColumn(name = "activity_requests", referencedColumnName = "id", nullable = false)
    public Set<ActivityRequests> getActivityRequests() {
        return activityRequests;
    }

    public void setActivityRequests(Set<ActivityRequests> activityRequests) {
        this.activityRequests = activityRequests;
    }
}
