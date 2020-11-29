package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.SalaryAdvanceApplication;

import java.util.List;

@Repository
public interface SalaryAdvanceApplicationRepository extends JpaRepository<SalaryAdvanceApplication,Long> {
    List<SalaryAdvanceApplication> findAllByEmployee(Employee employee);

    List<SalaryAdvanceApplication> findAllByReceivingManager(Employee employee);

    List<SalaryAdvanceApplication> findAllByHasBeenApproved(Boolean hasBeenApproved);
}
