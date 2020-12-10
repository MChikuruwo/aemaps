package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao.EmployeeRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dao.ActivityRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dao.ActivityRequestRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums.ActivityRequestAction;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums.ActivityRequestStatus;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums.ActivityStatus;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models.Activity;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models.ActivityRequests;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
public class ActivityRequestServiceImpl implements ActivityRequestService{

    private final ActivityRequestRepository activityRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final ActivityRepository activityRepository;

    public ActivityRequestServiceImpl(ActivityRequestRepository activityRequestRepository, EmployeeRepository employeeRepository, ActivityRepository activityRepository) {
        this.activityRequestRepository = activityRequestRepository;
        this.employeeRepository = employeeRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public List<ActivityRequests> getAll() {
        return activityRequestRepository.findAll();    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public String makeAddActivityRequest(long employeeId, long activityId){
    Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
            new IllegalArgumentException("Invalid employee id: " + employeeId));
    Activity activity = activityRepository.findById(activityId).orElseThrow(() ->
            new IllegalArgumentException("Invalid activity id: " + activityId));

    long currentActivityRequestsCount = activityRequestRepository
            .findByActivity_IdAndEmployee_Id(activityId, employeeId)
            .stream()
            .filter(activityRequest ->
                    activityRequest.getActivityRequestAction().equals(ActivityRequestAction.ADD) &&
                            activityRequest.getActivityRequestStatus().equals(ActivityRequestStatus.PENDING))
            .count();
        if (currentActivityRequestsCount > 0) {
        log.info("User already send activity request");
        return "Activity Request Will be Processed";
    }

        switch (activity.getActivityStatus()) {
        case COMPLETED:
            log.info("Activity already completed");
            break;
        case ACTIVE: {
            if (activity.getEmployee().contains(employee)) {
                log.info("User already in activity");
                break;
            }
            ActivityRequests activityRequest = ActivityRequests.builder()
                    .employee(employee)
                    .activity(activity)
                    .activityRequestAction(ActivityRequestAction.ADD)
                    .activityRequestStatus(ActivityRequestStatus.PENDING)
                    .requestDate(LocalDateTime.now())
                    .build();
            activityRequestRepository.save(activityRequest);
            break;
        }
        case PENDING: {
            ActivityRequests activityRequest = ActivityRequests.builder()
                    .employee(employee)
                    .activity(activity)
                    .activityRequestAction(ActivityRequestAction.ADD)
                    .activityRequestStatus(ActivityRequestStatus.PENDING)
                    .requestDate(LocalDateTime.now())
                    .build();
            activityRequestRepository.save(activityRequest);
            break;
        }
    }
        return "Activity Request Will be Processed";
}

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public String makeCompleteActivityRequest(long userId, long activityId) {
        Employee user = employeeRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("Invalid user id: " + userId));
        Activity activity = activityRepository.findById(activityId).orElseThrow(() ->
                new IllegalArgumentException("Invalid activity id: " + activityId));

        long currentActivityRequestsCount = activityRequestRepository
                .findByActivity_IdAndEmployee_Id(activityId, userId)
                .stream()
                .filter(activityRequest ->
                        activityRequest.getActivityRequestAction().equals(ActivityRequestAction.COMPLETE) &&
                                activityRequest.getActivityRequestStatus().equals(ActivityRequestStatus.PENDING))
                .count();
        if (currentActivityRequestsCount > 0) {
            log.info("User already sent activity request");
            return "";
        }

        switch (activity.getActivityStatus()) {
            case COMPLETED:
                log.info("Activity already completed");
                break;
            case ACTIVE:
                if (activity.getEmployee().contains(user)) {
                    ActivityRequests activityRequest = ActivityRequests.builder()
                            .employee(user)
                            .activity(activity)
                            .activityRequestAction(ActivityRequestAction.COMPLETE)
                            .activityRequestStatus(ActivityRequestStatus.PENDING)
                            .requestDate(LocalDateTime.now())
                            .build();
                    activityRequestRepository.save(activityRequest);
                    return "";
                }
                break;
            case PENDING:
                log.info("User can not complete pending activity");
                break;
        }
        return "";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public String approveAddActivityRequest(long activityRequestId) {
        ActivityRequests activityRequest = findActivityRequestById(activityRequestId);

        Activity activity = activityRequest.getActivity();
        Employee user = activityRequest.getEmployee();

        switch (activity.getActivityStatus()) {
            case PENDING: {
                activity.setStartTime(LocalDateTime.now());
                activity.setActivityStatus(ActivityStatus.ACTIVE);
                user.getActivities().add(activity);
                activityRequest.setActivityRequestStatus(ActivityRequestStatus.APPROVED);

                activityRepository.save(activity);
                log.info("Activity request approved");
                break;
            }
            case ACTIVE: {
                user.getActivities().add(activity);
                activityRequest.setActivityRequestStatus(ActivityRequestStatus.APPROVED);

                activityRepository.save(activity);
                log.info("Activity request approved");
                break;
            }
            case COMPLETED: {
                log.info("Can not approve add request for completed activity");
                activityRequest.setActivityRequestStatus(ActivityRequestStatus.REJECTED);
                break;
            }
        }
        activityRequestRepository.save(activityRequest);
        return "Activity Request has been approved";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public String approveCompleteActivityRequest(long activityRequestId) {
        ActivityRequests activityRequest = findActivityRequestById(activityRequestId);

        Activity activity = activityRequest.getActivity();

        switch (activity.getActivityStatus()) {
            case PENDING: {
                log.info("Can not approve complete request for pending activity");
                break;
            }
            case ACTIVE: {
                activity.setEndTime(LocalDateTime.now());
                activity.setActivityStatus(ActivityStatus.COMPLETED);
                activityRequest.setActivityRequestStatus(ActivityRequestStatus.APPROVED);
                activityRepository.save(activity);
                activityRequestRepository.save(activityRequest);
                break;
            }
            case COMPLETED: {
                log.info("Can not approve complete request for completed activity");
                activityRequest.setActivityRequestStatus(ActivityRequestStatus.REJECTED);
                activityRequestRepository.save(activityRequest);
                break;
            }
        }
        return "Activity has been completed";
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public String rejectActivityRequest(long activityRequestId) {
        ActivityRequests activityRequest = findActivityRequestById(activityRequestId);
        activityRequest.setActivityRequestStatus(ActivityRequestStatus.REJECTED);
        activityRequestRepository.save(activityRequest);

        return "Activity Request has been rejected";
    }

    @Override
    public ActivityRequests findActivityRequestById(long activityRequestId) {
        return activityRequestRepository.findById(activityRequestId).orElseThrow(() ->
                new IllegalArgumentException("Invalid activity request id: " + activityRequestId));
    }
}
