package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.AddSalaryAdvanceDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.UpdateSalaryAdvance;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.SalaryAdvance;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.SalaryAdvanceApplication;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.api.ApiResponse;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.EmployeeService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.SalaryAdvanceApplicationService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.SalaryAdvanceService;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/salary-advances", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/salary-advances", produces = MediaType.APPLICATION_JSON_VALUE)
public class SalaryAdvanceController {

    private final SalaryAdvanceService salaryAdvanceService;
    private final EmployeeService employeeService;
    private final SalaryAdvanceApplicationService salaryAdvanceApplicationService;
    private final ModelMapper modelMapper;

    @Autowired
    public SalaryAdvanceController(SalaryAdvanceService salaryAdvanceService, EmployeeService employeeService, SalaryAdvanceApplicationService salaryAdvanceApplicationService, ModelMapper modelMapper) {
        this.salaryAdvanceService = salaryAdvanceService;
        this.employeeService = employeeService;
        this.salaryAdvanceApplicationService = salaryAdvanceApplicationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all  salary advances", response = ApiResponse.class)
    public ApiResponse getAllSalaryAdvances(){
        return new ApiResponse(200, "SUCCESS", salaryAdvanceService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a salary advance by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse getSalaryAdvanceById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", salaryAdvanceService.getOne(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a salary advance by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteSalaryAdvance(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", salaryAdvanceService.delete(id));
    }

    @PostMapping("/add/{salary-advance-application-id}/{employee-id}")
    @ApiOperation(value = "Add a new loan. Takes salaryAdvanceId and employee-id as path variables",
            response = ApiResponse.class)
    public ApiResponse addSalaryAdvance(@RequestBody AddSalaryAdvanceDto salaryAdvanceDto,
                                   @PathVariable("salary-advance-application-id") Long salaryAdvanceId,
                                   @PathVariable("employee-id") Long employeeId){

        SalaryAdvance salaryAdvance = modelMapper.map(salaryAdvanceDto, SalaryAdvance.class);
        salaryAdvance.setSalaryAdvanceApplication(salaryAdvanceApplicationService.getOne(salaryAdvanceId));
        salaryAdvance.setEmployee(employeeService.getOne(employeeId));


        return new ApiResponse(200, "SUCCESS", salaryAdvanceService.add(salaryAdvance));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "Edit an existing salary advance", response = ApiResponse.class)
    public ApiResponse updateSalaryAdvance(@RequestBody UpdateSalaryAdvance updateSalaryAdvance){
        SalaryAdvance salaryAdvance = modelMapper.map(updateSalaryAdvance, SalaryAdvance.class);

        SalaryAdvance oldSalaryAdvance= salaryAdvanceService.getOne(updateSalaryAdvance.getId());

        salaryAdvance.setSalaryAdvanceApplication(oldSalaryAdvance.getSalaryAdvanceApplication());
        salaryAdvance.setEmployee(oldSalaryAdvance.getEmployee());

        return new ApiResponse(200, "SUCCESS", salaryAdvanceService.update(salaryAdvance));
    }

    @GetMapping("/by-salary-advance--application/{application-id}")
    @ApiOperation(value = "Get a salary advance  under a salary advance application. Takes salaryAdvanceApplicationId as a path variable", response = ApiResponse.class)
    public ApiResponse getSalaryAdvanceByApplication(@PathVariable("application-id") Long id){
        SalaryAdvanceApplication salaryAdvanceApplication = salaryAdvanceApplicationService.getOne(id);
        return new ApiResponse(200, "SUCCESS", salaryAdvanceService.findAllBySalaryAdvanceApplication(salaryAdvanceApplication));
    }

    @GetMapping("/by-employee/{employee-id}")
    @ApiOperation(value = "Get salary advances under an employee. Takes employeeId as a path variable", response = ApiResponse.class)
    public ApiResponse getSalaryAdvancesByEmployee(@PathVariable("employee-id") Long id){
        Employee employee = employeeService.getOne(id);
        return new ApiResponse(200, "SUCCESS", salaryAdvanceService.findAllByEmployee(employee));
    }

    @GetMapping("/by-salary-advance-application-and-employee/{salaryAdvance-id}/{employee-id}")
    @ApiOperation(value = "Get salary advances under a salary advance application and employee. Takes  salaryAdvanceApplicationId and employeeId as path variables", response = ApiResponse.class)
    public ApiResponse getSalaryAdvanceByApplicationAndEmployee(@PathVariable("salaryAdvance-id") Long loanApplicationId,
                                                               @PathVariable("employee-id") Long employeeId){
        SalaryAdvanceApplication salaryAdvanceApplication = salaryAdvanceApplicationService.getOne(loanApplicationId);
        Employee employee = employeeService.getOne(employeeId);

        return new ApiResponse(200, "SUCCESS", salaryAdvanceService.findAllBySalaryAdvanceApplicationAndEmployee(salaryAdvanceApplication,employee));

    }
}
