package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;


import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.EmployeeStatus;

import java.util.List;

public interface EmployeeStatusService {
    String add(EmployeeStatus employeeStatus);
    String update(EmployeeStatus employeeStatus);
    String delete(Long id);
    List<EmployeeStatus> getAll();
    EmployeeStatus getOne(Long id);

    EmployeeStatus findByStatus(String status);
}
