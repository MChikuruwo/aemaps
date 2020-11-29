package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.EmployeeStatus;
import zw.co.stewardbank.hrautomationplatform.models.JobTitle;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUserId(Integer userId);
    Employee findByEmployeeCode(String employeeCode);
    List<Employee> findAllByEmployeeStatus(EmployeeStatus employeeStatus);
    List<Employee> findAllByJobTitle(JobTitle jobTitle);
    List<Employee> findAllByResidentialStatus(String residentialStatus);
    List<Employee> findAllByGender(String gender);

}
