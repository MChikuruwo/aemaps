package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.AddAnnualActivityCalendarDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.AnnualActivityCalendar;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.api.ApiResponse;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.AnnualActivityCalendarService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/calendar", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/calendar", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnnualActivityCalendarController {

    private final AnnualActivityCalendarService annualActivityCalendarService;
    private final ModelMapper modelMapper;

    @Autowired
    public AnnualActivityCalendarController(AnnualActivityCalendarService annualActivityCalendarService, ModelMapper modelMapper) {
        this.annualActivityCalendarService = annualActivityCalendarService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all calendar activities", response = ApiResponse.class)
    public ApiResponse getAllCalendarActivities(){
        return new ApiResponse(200, "SUCCESS", annualActivityCalendarService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get one calendar activity by its ID", response = ApiResponse.class)
    public ApiResponse getOneCalendarActivityById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", annualActivityCalendarService.getOne(id));

    }

    @PostMapping("/add")
    @ApiOperation(value = "Add a new activity to calendar", response = ApiResponse.class)
    public ApiResponse addJobCalendarActivity(@RequestBody AddAnnualActivityCalendarDto annualActivityCalendarDto){

        AnnualActivityCalendar annualActivityCalendar = modelMapper.map(annualActivityCalendarDto, AnnualActivityCalendar.class);


        return new ApiResponse(200, "SUCCESS", annualActivityCalendarService.add(annualActivityCalendar));
    }

    @GetMapping("/activity-by-title")
    @ApiOperation(value = "Get one calendar activity by its title", response = ApiResponse.class)
    public ApiResponse getActivityByName(@RequestParam("activity") String activity){
        return new ApiResponse(200, "SUCCESS", annualActivityCalendarService.findByActivity(activity));
    }
}
