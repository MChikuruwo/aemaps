package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.SalaryAdvanceApplication;


import java.util.List;

@Repository
public interface SalaryAdvanceApplicationRepository extends JpaRepository<SalaryAdvanceApplication,Long> {
    List<SalaryAdvanceApplication> findAllByEmployee(Employee employee);

    List<SalaryAdvanceApplication> findAllByReceivingManager(Employee employee);

    List<SalaryAdvanceApplication> findAllByHasBeenApproved(Boolean hasBeenApproved);
}
