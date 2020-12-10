package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.api.ApiResponse;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.EmployeeService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums.ActivityRequestStatus;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models.ActivityRequests;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.services.ActivityRequestService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.services.ActivityService;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/activity/request", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/activity/request", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivityRequestController {

    private final ActivityRequestService activityRequestService;
    private final ActivityService activityService;
    private final EmployeeService employeeService;
    private  final ModelMapper modelMapper;

    public ActivityRequestController(ActivityRequestService activityRequestService,ActivityService activityService,EmployeeService employeeService,ModelMapper modelMapper){
        this.activityRequestService = activityRequestService;
        this.activityService = activityService;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all activity requests", response = ApiResponse.class)
    public ApiResponse getAllActivityRequests(){

        return new ApiResponse(200, "SUCCESS", activityRequestService.getAll());
    }

    @GetMapping("/add/{employee-id}/{activity-id}")
    @ApiOperation(value = "Get added activity request. Takes employee and activity Ids as path variables", response = ApiResponse.class)
    public ApiResponse getAddActivityRequests(@PathVariable("employee-id")Long employeeId,
                                                @PathVariable("activity-id")Long activityId){
         employeeService.getOne(employeeId);
         activityService.findActivityById(activityId);

        return new ApiResponse(200, "SUCCESS", activityRequestService.makeAddActivityRequest(employeeId,activityId));
    }

    @GetMapping("/add/{employee-id}/{activity-id}")
    @ApiOperation(value = "Get complete activity request. Takes employee and activity Ids as path variables", response = ApiResponse.class)
    public ApiResponse getCompleteActivityRequests(@PathVariable("employee-id")Long employeeId,
                                              @PathVariable("activity-id")Long activityId){
        employeeService.getOne(employeeId);
        activityService.findActivityById(activityId);

        return new ApiResponse(200, "SUCCESS", activityRequestService.makeCompleteActivityRequest(employeeId,activityId));
    }

    @GetMapping("/approve/{request-id}")
    @ApiOperation(value = "Get  activity request to approve. Takes activity request Id as pathVariable", response = ApiResponse.class)
    public ApiResponse getApprovedActivityRequests(@PathVariable("request-id")Long activityRequestId){

        ActivityRequests activityRequest =  activityRequestService.findActivityRequestById(activityRequestId);

        if (!activityRequest.getActivityRequestStatus().equals(ActivityRequestStatus.PENDING)) {
            return new ApiResponse(200, "SUCCESS");
        }

        switch (activityRequest.getActivityRequestAction()) {
            case ADD:
                activityRequestService.approveAddActivityRequest(activityRequestId);
                break;
            case COMPLETE:
                activityRequestService.approveCompleteActivityRequest(activityRequestId);
                break;
        }

        return new ApiResponse(200, "SUCCESS", activityRequestService.approveAddActivityRequest(activityRequestId));
    }

    @GetMapping("/reject/{request-id}")
    @ApiOperation(value = "Get  activity request to reject. Takes activity request Id as pathVariable", response = ApiResponse.class)
    public ApiResponse getRejectActivityRequests(@PathVariable("request-id")Long activityRequestId){

        ActivityRequests activityRequest =  activityRequestService.findActivityRequestById(activityRequestId);

        if (!activityRequest.getActivityRequestStatus().equals(ActivityRequestStatus.PENDING)) {
            return new ApiResponse(200, "SUCCESS");
        }

        return new ApiResponse(200, "SUCCESS", activityRequestService.rejectActivityRequest(activityRequestId));
    }
}
