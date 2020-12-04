package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.AddAnnualActivityCalendarDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.UpdateAnnualActivityCalendarDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.AnnualActivityCalendar;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.api.ApiResponse;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.AnnualActivityCalendarService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.AnnualActivityCategoryService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/calendar", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/calendar", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnnualActivityCalendarController {

    private final AnnualActivityCalendarService annualActivityCalendarService;
    private final AnnualActivityCategoryService annualActivityCategoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public AnnualActivityCalendarController(AnnualActivityCalendarService annualActivityCalendarService,AnnualActivityCategoryService annualActivityCategoryService ,ModelMapper modelMapper) {
        this.annualActivityCalendarService = annualActivityCalendarService;
        this.annualActivityCategoryService = annualActivityCategoryService;
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

    @PostMapping("/add/{category-id}")
    @ApiOperation(value = "Add a new activity to calendar", response = ApiResponse.class)
    public ApiResponse addJobCalendarActivity(@RequestBody AddAnnualActivityCalendarDto annualActivityCalendarDto,
                                              @PathVariable("category-id") Long categoryId){

        AnnualActivityCalendar annualActivityCalendar = modelMapper.map(annualActivityCalendarDto, AnnualActivityCalendar.class);
        annualActivityCalendar.setActivityCategory(annualActivityCategoryService.getOne(categoryId));


        return new ApiResponse(200, "SUCCESS", annualActivityCalendarService.add(annualActivityCalendar));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "Update an existing Calendar Activity", response = ApiResponse.class)
    public ApiResponse updateCalendarActivity(@RequestBody UpdateAnnualActivityCalendarDto annualActivityCalendarDto){
        AnnualActivityCalendar annualActivityCalendar = modelMapper.map(annualActivityCalendarDto, AnnualActivityCalendar.class);
        return new ApiResponse(200, "SUCCESS", annualActivityCalendarService.update(annualActivityCalendar));
    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete an activity by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteActivity(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", annualActivityCalendarService.delete(id));
    }

    @GetMapping("/activity-by-title")
    @ApiOperation(value = "Get one calendar activity by its title", response = ApiResponse.class)
    public ApiResponse getActivityByName(@RequestParam("activity") String activity){
        return new ApiResponse(200, "SUCCESS", annualActivityCalendarService.findByActivity(activity));
    }
}
