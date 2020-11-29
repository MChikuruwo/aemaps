package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.stewardbank.hrautomationplatform.dto.AddSpouseDetailsDto;
import zw.co.stewardbank.hrautomationplatform.dto.UpdateSpouseDetailsDto;
import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.SpouseDetails;
import zw.co.stewardbank.hrautomationplatform.models.api.ApiResponse;
import zw.co.stewardbank.hrautomationplatform.services.EmployeeService;
import zw.co.stewardbank.hrautomationplatform.services.SpouseDetailsService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/spouse-details", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/spouse-details", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpouseDetailsController {

    private final SpouseDetailsService spouseDetailsService;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @Autowired
    public SpouseDetailsController(SpouseDetailsService spouseDetailsService, EmployeeService employeeService, ModelMapper modelMapper) {
        this.spouseDetailsService = spouseDetailsService;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all spouse details", response = ApiResponse.class)
    public ApiResponse getAllSpouseDetails(){
        return new ApiResponse(200, "SUCCESS", spouseDetailsService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a spouse details by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse getSpouseDetailsById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", spouseDetailsService.getOne(id));
    }

    @GetMapping("/by-employee/{employee-id}")
    @ApiOperation(value = "Get spouse details by employee. Takes employeeId as a path variable", response = ApiResponse.class)
    public ApiResponse getSpouseDetailsByEmployee(@PathVariable("employee-id") Long employeeId){
        Employee employee = employeeService.getOne(employeeId);
        return new ApiResponse(200, "SUCCESS", spouseDetailsService.findByEmployee(employee));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete spouse details by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteSpouseDetails(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", spouseDetailsService.delete(id));
    }

    @PostMapping("/add/{employee-id}")
    @ApiOperation(value = "Add new spouse details for an employee. Takes employeeId as a path variable", response = ApiResponse.class)
    public ApiResponse addSpouseDetails(@RequestBody AddSpouseDetailsDto spouseDetailsDto,
                                        @PathVariable("employee-id") Long employeeId){

        SpouseDetails spouseDetails = modelMapper.map(spouseDetailsDto, SpouseDetails.class);
        spouseDetails.setEmployee(employeeService.getOne(employeeId));
        return new ApiResponse(201, "SUCCESS", spouseDetailsService.add(spouseDetails));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "Update existing spouse details for an employee", response = ApiResponse.class)
    public ApiResponse updateSpouseDetails(@RequestBody UpdateSpouseDetailsDto spouseDetailsDto){
        SpouseDetails spouseDetails = modelMapper.map(spouseDetailsDto, SpouseDetails.class);

        // Get old record to maintain the employee
        SpouseDetails oldRecord = spouseDetailsService.getOne(spouseDetailsDto.getId());

        spouseDetails.setEmployee(oldRecord.getEmployee());
        return new ApiResponse(200, "SUCCESS", spouseDetailsService.update(spouseDetails));
    }
}
