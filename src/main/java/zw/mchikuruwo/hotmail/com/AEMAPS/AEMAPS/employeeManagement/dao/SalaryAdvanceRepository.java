package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.SalaryAdvance;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.SalaryAdvanceApplication;


import java.util.List;

@Repository
public interface SalaryAdvanceRepository extends JpaRepository<SalaryAdvance, Long> {
    List<SalaryAdvance> findAllByEmployee(Employee employee);

    List<SalaryAdvance> findAllBySalaryAdvanceApplication(SalaryAdvanceApplication salaryAdvanceApplication);

    List<SalaryAdvance> findAllBySalaryAdvanceApplicationAndEmployee(SalaryAdvanceApplication salaryAdvanceApplication, Employee employee);
}
