package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dto;

import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums.ActivityImportance;

import java.sql.Timestamp;

public class AddActivityDto {
    private String name;
    private String description;
    private ActivityImportance activityImportance;

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
}
