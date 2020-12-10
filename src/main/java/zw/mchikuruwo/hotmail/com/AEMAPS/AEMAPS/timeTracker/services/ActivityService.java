package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.services;

import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dto.ActivityDurationDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> getAll();
    String createActivity(Activity activity);
    Activity findActivityById(long activityId);
    String deleteActivity(long id);
    String markTimeSpent(long activityId, Employee user, ActivityDurationDto durationDTO);
}
