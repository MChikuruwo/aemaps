package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dto;

import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums.ActivityImportance;

import java.sql.Timestamp;

public class UpdateActivityDto {
    private Long id;
    private String name;
    private String description;
    private ActivityImportance activityImportance;
    private Timestamp startTime;
    private Timestamp endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityImportance getActivityImportance() {
        return activityImportance;
    }

    public void setActivityImportance(ActivityImportance activityImportance) {
        this.activityImportance = activityImportance;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
