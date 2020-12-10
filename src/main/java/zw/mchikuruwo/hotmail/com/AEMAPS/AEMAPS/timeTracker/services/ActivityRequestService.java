package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.services;


import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models.ActivityRequests;

import java.util.List;

public interface ActivityRequestService {
    List<ActivityRequests> getAll();
    String makeAddActivityRequest(long employeeId, long activityId);
    String makeCompleteActivityRequest(long employeeId, long activityId);
    String approveAddActivityRequest(long activityRequestId);
    String rejectActivityRequest(long activityRequestId);
    String approveCompleteActivityRequest(long activityRequestId);
    ActivityRequests findActivityRequestById(long activityRequestId);
}
