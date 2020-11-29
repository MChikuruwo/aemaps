package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.DelegationOfDuty;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;

import java.util.List;

@Repository
public interface DelegationOfDutyRepository extends JpaRepository<DelegationOfDuty, Long> {
    List<DelegationOfDuty> findAllByAssignedEmployee(Employee employee);
    List<DelegationOfDuty> findAllByAssigningManager(Employee employee);

    List<DelegationOfDuty> findAllByAssigningManagerAndAssignedEmployee(Employee manager, Employee employee);
}
