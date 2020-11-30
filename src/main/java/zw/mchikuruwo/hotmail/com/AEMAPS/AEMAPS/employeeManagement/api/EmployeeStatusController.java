package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services.EmployeeStatusService;

@RestController
public class EmployeeStatusController {

    private final EmployeeStatusService employeeStatusService;

    @Autowired
    public EmployeeStatusController(EmployeeStatusService employeeStatusService) {
        this.employeeStatusService = employeeStatusService;
    }
}
