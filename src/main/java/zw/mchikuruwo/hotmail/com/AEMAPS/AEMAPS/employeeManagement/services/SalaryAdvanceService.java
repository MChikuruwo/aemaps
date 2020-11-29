package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;



import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.SalaryAdvance;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.SalaryAdvanceApplication;

import java.util.List;

public interface SalaryAdvanceService {

    String add(SalaryAdvance salaryAdvance);
    String update(SalaryAdvance salaryAdvance);
    String delete(Long id);
    List<SalaryAdvance> getAll();
    SalaryAdvance getOne(Long id);

    List<SalaryAdvance> findAllByEmployee(Employee employee);
    List<SalaryAdvance> findAllBySalaryAdvanceApplication(SalaryAdvanceApplication salaryAdvanceApplication);
    List<SalaryAdvance> findAllBySalaryAdvanceApplicationAndEmployee(SalaryAdvanceApplication salaryAdvanceApplication, Employee employee);

}
