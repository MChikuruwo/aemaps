package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.SalaryAdvance;
import zw.co.stewardbank.hrautomationplatform.models.SalaryAdvanceApplication;

import java.util.List;

@Repository
public interface SalaryAdvanceRepository extends JpaRepository<SalaryAdvance, Long> {
    List<SalaryAdvance> findAllByEmployee(Employee employee);

    List<SalaryAdvance> findAllBySalaryAdvanceApplication(SalaryAdvanceApplication salaryAdvanceApplication);

    List<SalaryAdvance> findAllBySalaryAdvanceApplicationAndEmployee(SalaryAdvanceApplication salaryAdvanceApplication, Employee employee);
}
