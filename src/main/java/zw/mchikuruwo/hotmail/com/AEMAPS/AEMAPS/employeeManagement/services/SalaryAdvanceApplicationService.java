package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;



import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.SalaryAdvanceApplication;

import java.util.List;

public interface SalaryAdvanceApplicationService {
    String add(SalaryAdvanceApplication salaryAdvanceApplication);
    String update(SalaryAdvanceApplication salaryAdvanceApplication);
    String delete(Long id);
    List<SalaryAdvanceApplication> getAll();
    SalaryAdvanceApplication getOne(Long id);

    List<SalaryAdvanceApplication> findAllByEmployeeCode(Employee employee);
    List<SalaryAdvanceApplication> findAllBySignOffManager(Employee employee);
    List<SalaryAdvanceApplication> findAllByHasBeenApproved(Boolean hasBeenApproved);
}
