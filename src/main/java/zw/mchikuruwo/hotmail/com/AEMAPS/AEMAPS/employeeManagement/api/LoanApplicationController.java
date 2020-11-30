package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.AddLoanApplicationDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.LoanApplication;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.LoanType;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.api.ApiResponse;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.EmployeeService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.LoanApplicationService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.LoanTypeService;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/loan-applications", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/loan-applications", produces = MediaType.APPLICATION_JSON_VALUE)

public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;
    private final LoanTypeService loanTypeService;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @Autowired
    public LoanApplicationController(LoanApplicationService loanApplicationService,LoanTypeService loanTypeService, EmployeeService employeeService, ModelMapper modelMapper) {
        this.loanApplicationService = loanApplicationService;
        this.loanTypeService = loanTypeService;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all loan applications", response = ApiResponse.class)
    public ApiResponse getAllLoanApplications(){
        return new ApiResponse(200, "SUCCESS", loanApplicationService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a loan application record by its id. Takes id as a path variable",
            response = ApiResponse.class)
    public ApiResponse getLoanApplicationById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", loanApplicationService.getOne(id));
    }

    @GetMapping("/loanApplicant-employee-id/{id}")
    @ApiOperation(value = "Get a loan application record by the requesting employees. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse getLoanApplicationsRequested(@PathVariable("id") Long employeeId){
        Employee loanApplicant = employeeService.getOne(employeeId);
        return new ApiResponse(200, "SUCCESS",
                loanApplicationService.findAllByEmployeeCode(loanApplicant));
    }
    @GetMapping("/by-loan-type/{loanType-id}")
    @ApiOperation(value = "Get loan applications records by loan type. Takes loanTypeId as a path variable", response = ApiResponse.class)
    public ApiResponse getLoanApplicationsByLoanType(@PathVariable("loanType-id") Integer loanTypeId){
        LoanType loanApplicationType =  loanTypeService.getOne(loanTypeId);
        return new ApiResponse(200, "SUCCESS", loanApplicationService.findAllByLoanType(loanApplicationType));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a loan application record. Takes id as a path variable",
            response = ApiResponse.class)
    public ApiResponse deleteLoanApplication(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", loanApplicationService.delete(id));
    }

    @PostMapping("/create/{employee-id}/{loan-type-id}")
    @ApiOperation(value = "Create a new loan application record. " +
            "Takes employee Id and loanType Id  as path variables",
            response = ApiResponse.class)
    public ApiResponse createLoanApplication(@RequestBody AddLoanApplicationDto loanApplicationDto,
                                           @PathVariable("employee-id") Long employeeId,
                                           @PathVariable("loan-type-id") Integer loanTypeId)
                                           {

        LoanApplication loanApplication = modelMapper.map(loanApplicationDto, LoanApplication.class);
        loanApplication.setLoanType(loanTypeService.getOne(loanTypeId));
        loanApplication.setEmployee(employeeService.getOne(employeeId));
        loanApplication.setHasBeenApproved(false);

        return new ApiResponse(200, "SUCCESS", loanApplicationService.add(loanApplication));
    }


    @GetMapping("/loan-approval-status")
    @ApiOperation(value = "Get Loan application records by approval status. Takes approved status as a request parameter",
            response = ApiResponse.class)
    public ApiResponse getLoanApplicationByApprovalStatus(@RequestParam("approved") Boolean hasBeenApproved){
        return new ApiResponse(200, "SUCCESS",
                loanApplicationService.findAllByHasBeenApproved(hasBeenApproved));
    }

}
