package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dto;

import java.sql.Timestamp;

public class AddActivityDto {
    private String name;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;

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
