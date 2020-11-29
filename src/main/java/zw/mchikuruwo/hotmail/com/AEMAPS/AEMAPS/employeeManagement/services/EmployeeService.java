package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;


import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.EmployeeStatus;
import zw.co.stewardbank.hrautomationplatform.models.JobTitle;

import java.util.List;

public interface EmployeeService {
    String add(Employee employee);
    String update(Employee employee);
    String delete(Long id);
    List<Employee> getAll();
    Employee getOne(Long id);

    Employee findByUserId(Integer userId);
    Employee findByEmployeeCode(String employeeCode);
    List<Employee> findAllByEmployeeStatus(EmployeeStatus employeeStatus);
    List<Employee> findAllByJobTitle(JobTitle jobTitle);
    List<Employee> findAllByResidentialStatus(String residentialStatus);
    List<Employee> findAllByGender(String gender);
}
