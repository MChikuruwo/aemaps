package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto;

import java.sql.Date;

public class AddAnnualActivityCalendarDto {
    private String activity;
    private Date activityDay;

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getActivityDay() {
        return activityDay;
    }

    public void setActivityDay(Date activityDay) {
        this.activityDay = activityDay;
    }
}
