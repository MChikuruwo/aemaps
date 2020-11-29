package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.stewardbank.hrautomationplatform.dto.AddNextOfKinDto;
import zw.co.stewardbank.hrautomationplatform.dto.UpdateNextOfKinDto;
import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.NextOfKin;
import zw.co.stewardbank.hrautomationplatform.models.api.ApiResponse;
import zw.co.stewardbank.hrautomationplatform.services.EmployeeService;
import zw.co.stewardbank.hrautomationplatform.services.NextOfKinService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/next-of-kin", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/next-of-kin", produces = MediaType.APPLICATION_JSON_VALUE)
public class NextOfKinController {

    private final NextOfKinService nextOfKinService;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @Autowired
    public NextOfKinController(NextOfKinService nextOfKinService, EmployeeService employeeService, ModelMapper modelMapper) {
        this.nextOfKinService = nextOfKinService;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all next of kins", response = ApiResponse.class)
    public ApiResponse getAllNextOfKins(){
        return new ApiResponse(200, "SUCCESS", nextOfKinService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a next of kin by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse getNextOfKinById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", nextOfKinService.getOne(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a next of kin by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteNextOfKin(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", nextOfKinService.delete(id));
    }

    @PostMapping("/add/{employee-id}")
    @ApiOperation(value = "Add an employee next of kin. Takes employeeId as a path variable", response = ApiResponse.class)
    public ApiResponse addNextOfKin(@RequestBody AddNextOfKinDto nextOfKinDto,
                                    @PathVariable("employee-id") Long employeeId){
        NextOfKin nextOfKin = modelMapper.map(nextOfKinDto, NextOfKin.class);

        Employee employee = employeeService.getOne(employeeId);

        nextOfKin.setEmployee(employee);

        return new ApiResponse(201, "SUCCESS", nextOfKinService.add(nextOfKin));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "Update an existing next of kin", response = ApiResponse.class)
    public ApiResponse updateNextOfKinDetails(@RequestBody UpdateNextOfKinDto nextOfKinDto){
        NextOfKin nextOfKin = modelMapper.map(nextOfKinDto, NextOfKin.class);
        // Get employee from old record
        NextOfKin oldRecord = nextOfKinService.getOne(nextOfKinDto.getId());

        nextOfKin.setEmployee(oldRecord.getEmployee());
        return new ApiResponse(200, "SUCCESS", nextOfKinService.update(nextOfKin));
    }

    @GetMapping("/by-employee/{employee-id}")
    @ApiOperation(value = "Get a next of kin for a specific employee. Takes employeeId as a path variable", response = ApiResponse.class)
    public ApiResponse findNextOfKinByEmployee(@PathVariable("employee-id") Long employeeId){
        Employee employee = employeeService.getOne(employeeId);
        return new ApiResponse(200, "SUCCESS", nextOfKinService.findByEmployee(employee));
    }
}
