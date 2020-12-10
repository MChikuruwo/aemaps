package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.api.ApiResponse;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.EmployeeService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dto.ActivityDurationDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dto.AddActivityDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models.Activity;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.services.ActivityService;

@Log4j2
@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/activity", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/activity", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivityController {

    private final ActivityService activityService;
    private final ModelMapper modelMapper;
    private final EmployeeService employeeService;


    public ActivityController(ActivityService activityService,EmployeeService employeeService, ModelMapper modelMapper) {
        this.activityService = activityService;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all activities", response = ApiResponse.class)
    public ApiResponse getAllActivities(){

        return new ApiResponse(200, "SUCCESS", activityService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get an activity/task by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse getActivityById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", activityService.findActivityById(id));
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete an activity/task by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteActivity(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", activityService.deleteActivity(id));
    }


    @PostMapping("/add")
    @ApiOperation(value = "Add a new activity/task", response = ApiResponse.class)
    public ApiResponse addActivity(@RequestBody AddActivityDto activityDto) {

        Activity activity = modelMapper.map(activityDto, Activity.class);

        return new ApiResponse(200, "SUCCESS", activityService.createActivity(activity));

    }

    @PostMapping("/mark-time/{activity-id}/{employee-id}")
    @ApiOperation(value = "Mark the time spent on a activity/task. Takes employee and activity Ids  as path variables.", response = ApiResponse.class)
    public ApiResponse markTimeSpent(@RequestBody ActivityDurationDto activityDurationDto,
                                     @PathVariable("activity-id")Long activityId,
                                     @PathVariable("employee-id")Long employeeId) {

        activityService.findActivityById(activityId);


        return new ApiResponse(200, "SUCCESS", activityService.markTimeSpent(activityId,employeeService.getOne(employeeId),activityDurationDto));

    }

}
