package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.AddLoanDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.UpdateLoanDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Loan;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.LoanApplication;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.api.ApiResponse;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.EmployeeService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.LoanApplicationService;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.LoanService;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/loans", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/loans", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {

    private final LoanService loanService;
    private final EmployeeService employeeService;
    private final LoanApplicationService loanApplicationService;
    private final ModelMapper modelMapper;

    @Autowired
    public LoanController(LoanService loanService, EmployeeService employeeService, LoanApplicationService loanApplicationService, ModelMapper modelMapper) {
        this.loanService = loanService;
        this.employeeService = employeeService;
        this.loanApplicationService = loanApplicationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all  loans", response = ApiResponse.class)
    public ApiResponse getAllLoans(){
        return new ApiResponse(200, "SUCCESS", loanService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a loan by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse getLoanById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", loanService.getOne(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a loan by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteLoan(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", loanService.delete(id));
    }

    @PostMapping("/add/{loan-application-id}/{employee-id}")
    @ApiOperation(value = "Add a new loan. Takes loanApplicationId and employee-id as path variables",
            response = ApiResponse.class)
    public ApiResponse addLoan(@RequestBody AddLoanDto loanDto,
                                   @PathVariable("loan-application-id") Long loanApplicationId,
                                   @PathVariable("employee-id") Long employeeId){

        Loan loan = modelMapper.map(loanDto, Loan.class);
      loan.setLoanApplication(loanApplicationService.getOne(loanApplicationId));
      loan.setEmployee(employeeService.getOne(employeeId));


        return new ApiResponse(200, "SUCCESS", loanService.add(loan));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "Edit an existing loan", response = ApiResponse.class)
    public ApiResponse updateLoan(@RequestBody UpdateLoanDto updateLoanDto){
        Loan loan = modelMapper.map(updateLoanDto, Loan.class);

        // Get loan application and employee from old database record
        Loan oldLoan = loanService.getOne(updateLoanDto.getId());

        loan.setLoanApplication(oldLoan.getLoanApplication());
        loan.setEmployee(oldLoan.getEmployee());

        return new ApiResponse(200, "SUCCESS", loanService.update(loan));
    }

    @GetMapping("/by-loan-application/{application-id}")
    @ApiOperation(value = "Get Loans under a loan application. Takes loanApplicationId as a path variable", response = ApiResponse.class)
    public ApiResponse getLoansByLoanApplication(@PathVariable("application-id") Long id){
        LoanApplication loanApplication = loanApplicationService.getOne(id);
        return new ApiResponse(200, "SUCCESS", loanService.findAllByLoanApplication(loanApplication));
    }

    @GetMapping("/by-employee/{employee-id}")
    @ApiOperation(value = "Get loans under an employee. Takes employeeId as a path variable", response = ApiResponse.class)
    public ApiResponse getLoansByEmployee(@PathVariable("employee-id") Long id){
        Employee employee = employeeService.getOne(id);
        return new ApiResponse(200, "SUCCESS", loanService.findAllByEmployee(employee));
    }

    @GetMapping("/by-loanApplication-and-employee/{loanApplication-id}/{employee-id}")
    @ApiOperation(value = "Get loans under a loan application and employee. Takes loanApplicationId and employeeId as path variables", response = ApiResponse.class)
    public ApiResponse getLoansByLoanApplicationAndEmployee(@PathVariable("loanApplication-id") Long loanApplicationId,
                                                               @PathVariable("employee-id") Long employeeId){
        LoanApplication loanApplication = loanApplicationService.getOne(loanApplicationId);
        Employee employee = employeeService.getOne(employeeId);

        return new ApiResponse(200, "SUCCESS", loanService.findAllByLoanApplicationAndEmployee(loanApplication,employee));

    }

}
