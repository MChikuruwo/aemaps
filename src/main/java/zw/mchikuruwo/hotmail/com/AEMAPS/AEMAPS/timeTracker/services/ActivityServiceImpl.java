package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.exceptions.EmployeeAlreadyExistsException;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dao.ActivityRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dto.ActivityDurationDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dto.AddActivityDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums.ActivityStatus;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models.Activity;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> getAll() {
        List<Activity> activities = activityRepository.findAll();
        if (activities.isEmpty()){
            throw new EntityNotFoundException("Activities not found!");
        }
        return activities;
    }
    @Override
    public String createActivity(Activity activity) {
        activityRepository.save(activity);
        return "Activity has been successfully added.";
    }


    @Override
    public Activity findActivityById(long activityId) {
        return activityRepository.findById(activityId).orElseThrow(() ->
                new IllegalArgumentException("Invalid activity id: " + activityId));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public String deleteActivity(long id) {
        Activity activity = findActivityById(id);
        Set<Employee> users = activity.getEmployee();
        for (Employee user : users) {
            user.getActivities().remove(activity);
        }
        activityRepository.delete(activity);
        return "Activity has been successfully deleted";
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public String markTimeSpent(long activityId, Employee user, ActivityDurationDto durationDTO) {
        Activity activity = findActivityById(activityId);

        if (activity.getActivityStatus().equals(ActivityStatus.ACTIVE) && activity.getEmployee().contains(user)) {
            Duration duration = activity.getDuration();

            duration = duration
                    .plusDays(durationDTO.getDays())
                    .plusHours(durationDTO.getHours())
                    .plusMinutes(durationDTO.getMinutes());

            activity.setDuration(duration);

            activityRepository.save(activity);
        }
        return "Duration Time has been successfully marked";
    }
}
