package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.AddSalaryAdvanceApplicationDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.UpdateSalaryAdvanceApplicationDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.SalaryAdvanceApplication;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.api.ApiResponse;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.EmployeeService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.SalaryAdvanceApplicationService;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/salary-advance-applications", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/salary-advance-applications", produces = MediaType.APPLICATION_JSON_VALUE)
public class SalaryAdvanceApplicationController {

    private final SalaryAdvanceApplicationService salaryAdvanceApplicationService;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @Autowired
    public SalaryAdvanceApplicationController(SalaryAdvanceApplicationService salaryAdvanceApplicationService, EmployeeService employeeService, ModelMapper modelMapper) {
        this.salaryAdvanceApplicationService = salaryAdvanceApplicationService;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all salary advance applications", response = ApiResponse.class)
    public ApiResponse getAllSalaryAdvanceApplications(){
        return new ApiResponse(200, "SUCCESS", salaryAdvanceApplicationService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a salary  advance record by its id. Takes id as a path variable",
            response = ApiResponse.class)
    public ApiResponse getSalaryAdvanceApplicationsById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", salaryAdvanceApplicationService.getOne(id));
    }

    @GetMapping("/by-signOff-manager/{id}")
    @ApiOperation(value = "Get a salary advance record by the signOff manager. Takes id as a path variable",
            response = ApiResponse.class)
    public ApiResponse getSalaryAdvanceApplicationsByReceivingManager(@PathVariable("id") Long managerId){
        Employee manager = employeeService.getOne(managerId);
        return new ApiResponse(200, "SUCCESS",
                salaryAdvanceApplicationService.findAllBySignOffManager(manager));
    }

    @GetMapping("/by-requesting-employee/{id}")
    @ApiOperation(value = "Get a Salary advance record by the requesting employee. Takes id as a path variable",
            response = ApiResponse.class)
    public ApiResponse getSalaryAdvanceApplicationByRequestingEmployee(@PathVariable("id") Long employeeId){
        Employee employee = employeeService.getOne(employeeId);
        return new ApiResponse(200, "SUCCESS",
                salaryAdvanceApplicationService.findAllByEmployeeCode(employee));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a salary advance record. Takes id as a path variable",
            response = ApiResponse.class)
    public ApiResponse deleteSalaryAdvanceApplication(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", salaryAdvanceApplicationService.delete(id));
    }

    @PostMapping("/create/{manager-id}/{employee-id}")
    @ApiOperation(value = "Create a new salary advance record. " +
            "Takes managerId and employeeId as path variables",
            response = ApiResponse.class)
    public ApiResponse createSalaryAdvanceApplication(@RequestBody AddSalaryAdvanceApplicationDto salaryAdvanceApplicationDto,
                                           @PathVariable("manager-id") Long managerId,
                                           @PathVariable("employee-id") Long employeeId){

        SalaryAdvanceApplication salaryAdvanceApplication = modelMapper.map(salaryAdvanceApplicationDto, SalaryAdvanceApplication.class);
        salaryAdvanceApplication.setReceivingManager(employeeService.getOne(employeeId));
        salaryAdvanceApplication.setEmployee(employeeService.getOne(managerId));
        salaryAdvanceApplication.setHasBeenApproved(false);

        return new ApiResponse(201, "SUCCESS", salaryAdvanceApplicationService.add(salaryAdvanceApplication));
    }

    @PutMapping("/edit/{employee-id}")
    @ApiOperation(value = "Edit an existing salary advance record. " +
            "Takes  employeeId as path variable",
            response = ApiResponse.class)
    public ApiResponse updateSalaryAdvanceApplication(@RequestBody UpdateSalaryAdvanceApplicationDto salaryAdvanceApplicationDto,
                                           @PathVariable("employee-id") Long employeeId){

        SalaryAdvanceApplication salaryAdvanceApplication = modelMapper.map(salaryAdvanceApplicationDto, SalaryAdvanceApplication.class);
        salaryAdvanceApplication.setEmployee(employeeService.getOne(employeeId));

        // Get details from old record
        SalaryAdvanceApplication oldRecord = salaryAdvanceApplicationService.getOne(salaryAdvanceApplicationDto.getId());

        salaryAdvanceApplication.setReceivingManager(oldRecord.getReceivingManager());
        return new ApiResponse(200, "SUCCESS", salaryAdvanceApplicationService.update(salaryAdvanceApplication));
    }

    @GetMapping("/by-approval-status")
    @ApiOperation(value = "Get salary advance records by approval status. Takes approved status as a request parameter",
            response = ApiResponse.class)
    public ApiResponse getSalaryAdvanceApplicationsByApprovalStatus(@RequestParam("approved") Boolean hasBeenApproved){
        return new ApiResponse(200, "SUCCESS",
                salaryAdvanceApplicationService.findAllByHasBeenApproved(hasBeenApproved));
    }

}
