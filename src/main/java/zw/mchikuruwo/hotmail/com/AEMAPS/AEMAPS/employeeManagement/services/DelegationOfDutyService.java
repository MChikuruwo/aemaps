package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;



import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.DelegationOfDuty;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;

import java.util.List;

public interface DelegationOfDutyService {

    String add(DelegationOfDuty delegationOfDuty);
    String update(DelegationOfDuty delegationOfDuty);
    String delete(Long id);
    List<DelegationOfDuty> getAll();
    DelegationOfDuty getOne(Long id);

    List<DelegationOfDuty> findAllByAssignedEmployee(Employee employee);
    List<DelegationOfDuty> findAllByAssigningManager(Employee manager);
    List<DelegationOfDuty> findAllByAssigningManagerAndAssignedEmployee(Employee manager, Employee employee);
}
