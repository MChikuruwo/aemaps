package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zw.co.stewardbank.hrautomationplatform.dto.AddStaffTransferDto;
import zw.co.stewardbank.hrautomationplatform.dto.UpdateStaffTransferDto;
import zw.co.stewardbank.hrautomationplatform.models.Department;
import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.StaffTransfer;
import zw.co.stewardbank.hrautomationplatform.models.api.ApiResponse;
import zw.co.stewardbank.hrautomationplatform.services.DepartmentService;
import zw.co.stewardbank.hrautomationplatform.services.EmployeeService;
import zw.co.stewardbank.hrautomationplatform.services.StaffTransferService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/staff-transfers", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/api/v1/staff-transfers", produces = MediaType.APPLICATION_JSON_VALUE)
public class StaffTransferController {

    private final StaffTransferService staffTransferService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;

    @Autowired
    public StaffTransferController(StaffTransferService staffTransferService, EmployeeService employeeService, DepartmentService departmentService, ModelMapper modelMapper) {
        this.staffTransferService = staffTransferService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get all staff transfers", response = ApiResponse.class)
    public ApiResponse getAllStaffTransfers(){
        return new ApiResponse(200, "SUCCESS", staffTransferService.getAll());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a staff transfer record by its id. Takes id as a path variable",
            response = ApiResponse.class)
    public ApiResponse getStaffTransferById(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", staffTransferService.getOne(id));
    }

    @GetMapping("/by-releasing-manager/{id}")
    @ApiOperation(value = "Get a staff transfer record by the releasing manager. Takes id as a path variable",
            response = ApiResponse.class)
    public ApiResponse getStaffTransfersByReleasingManager(@PathVariable("id") Long managerId){
        Employee manager = employeeService.getOne(managerId);
        return new ApiResponse(200, "SUCCESS",
                staffTransferService.findAllByReleasingManager(manager));
    }

    @GetMapping("/by-transferring-employee/{id}")
    @ApiOperation(value = "Get a staff transfer record by the transferring employee. Takes id as a path variable",
            response = ApiResponse.class)
    public ApiResponse getStaffTransfersByTransferringEmployee(@PathVariable("id") Long employeeId){
        Employee employee = employeeService.getOne(employeeId);
        return new ApiResponse(200, "SUCCESS",
                staffTransferService.findAllByTransferringEmployee(employee));
    }

    @GetMapping("/by-department-to-assign/{department-id}")
    @ApiOperation(value = "Get staff transfer records by department. Takes departmentId as a path variable",
            response = ApiResponse.class)
    public ApiResponse getStaffTransfersByDepartmentToAssign(@PathVariable("department-id") Integer departmentId){
        Department department = departmentService.getOne(departmentId);
        return new ApiResponse(200, "SUCCESS",
                staffTransferService.findAllByDepartmentToAssign(department));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete a staff transfer record. Takes id as a path variable",
            response = ApiResponse.class)
    public ApiResponse deleteStaffTransfer(@PathVariable("id") Long id){
        return new ApiResponse(200, "SUCCESS", staffTransferService.delete(id));
    }

    @PostMapping("/create/{manager-id}/{target-department-id}/{employee-id}")
    @ApiOperation(value = "Create a new staff transfer record. " +
            "Takes managerId, targetDepartmentId and employeeId as path variables",
            response = ApiResponse.class)
    public ApiResponse createStaffTransfer(@RequestBody AddStaffTransferDto staffTransferDto,
                                           @PathVariable("manager-id") Long managerId,
                                           @PathVariable("target-department-id") Integer departmentId,
                                           @PathVariable("employee-id") Long employeeId){

        StaffTransfer staffTransfer = modelMapper.map(staffTransferDto, StaffTransfer.class);
        staffTransfer.setReleasingManager(employeeService.getOne(employeeId));
        staffTransfer.setDepartmentToAssign(departmentService.getOne(departmentId));
        staffTransfer.setTransferringEmployee(employeeService.getOne(employeeId));
        staffTransfer.setHasBeenApproved(false);

        return new ApiResponse(201, "SUCCESS", staffTransferService.add(staffTransfer));
    }

    @PutMapping("/edit/{target-department-id}/{employee-id}")
    @ApiOperation(value = "Edit an existing staff transfer record. " +
            "Takes targetDepartmentId and employeeId as path variables",
            response = ApiResponse.class)
    public ApiResponse updateStaffTransfer(@RequestBody UpdateStaffTransferDto staffTransferDto,
                                           @PathVariable("target-department-id") Integer departmentId,
                                           @PathVariable("employee-id") Long employeeId){

        StaffTransfer staffTransfer = modelMapper.map(staffTransferDto, StaffTransfer.class);
        staffTransfer.setDepartmentToAssign(departmentService.getOne(departmentId));
        staffTransfer.setTransferringEmployee(employeeService.getOne(employeeId));

        // Get details from old record
        StaffTransfer oldRecord = staffTransferService.getOne(staffTransferDto.getId());

        staffTransfer.setReleasingManager(oldRecord.getReleasingManager());
        return new ApiResponse(200, "SUCCESS", staffTransferService.update(staffTransfer));
    }

    @GetMapping("/by-approval-status")
    @ApiOperation(value = "Get staff transfer records by approval status. Takes approved status as a request parameter",
            response = ApiResponse.class)
    public ApiResponse getStaffTransfersByApprovalStatus(@RequestParam("approved") Boolean hasBeenApproved){
        return new ApiResponse(200, "SUCCESS",
                staffTransferService.findAllByHasBeenApproved(hasBeenApproved));
    }
}
