package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.AddLoanTypeDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto.UpdateLoanTypeDto;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.LoanType;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.api.ApiResponse;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.LoanTypeService;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/loan-types", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/loan-types", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanTypeController {
    private final LoanTypeService loanTypeService;
    private final ModelMapper modelMapper;

    @Autowired
    public LoanTypeController(LoanTypeService loanTypeService, ModelMapper modelMapper) {
        this.loanTypeService = loanTypeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all loan types", response = ApiResponse.class)
    public ApiResponse getAllLoanTypes(){
        return new ApiResponse(200, "SUCCESS", loanTypeService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a loan type by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse getLoanTypeById(@PathVariable("id") Integer id){
        return new ApiResponse(200, "SUCCESS", loanTypeService.getOne(id));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a loan type by its id. Takes id as a path variable", response = ApiResponse.class)
    public ApiResponse deleteLoanType(@PathVariable("id") Integer id){
        return new ApiResponse(200, "SUCCESS", loanTypeService.delete(id));
    }


    @PostMapping("/by-loan-type-name")
    @ApiOperation(value = "Get a loan type by its name.", response = ApiResponse.class)
    public ApiResponse getLoanTypeByName(@RequestBody AddLoanTypeDto loanTypeDto){
        return new ApiResponse(200, "SUCCESS", loanTypeService.findByType(loanTypeDto.getType()));
    }

    @PostMapping("/add-loan-type")
    @ApiOperation(value = "Add a new loan type", response = ApiResponse.class)
    public ApiResponse addLoanType(@RequestBody AddLoanTypeDto loanTypeDto){
        LoanType loanType = modelMapper.map(loanTypeDto, LoanType.class);
        return new ApiResponse(201, "SUCCESS", loanTypeService.add(loanType));
    }

    @PutMapping("/edit-loan-type")
    @ApiOperation(value = "Update an existing loan type", response = ApiResponse.class)
    public ApiResponse updateLoanType(@RequestBody UpdateLoanTypeDto updateLoanTypeDto){
        LoanType loanType = modelMapper.map(updateLoanTypeDto, LoanType.class);
        return new ApiResponse(200, "SUCCESS", loanTypeService.update(loanType));
    }

}
