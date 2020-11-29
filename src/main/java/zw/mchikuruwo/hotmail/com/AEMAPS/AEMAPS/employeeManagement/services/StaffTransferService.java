package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;


import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Department;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.StaffTransfer;

import java.util.List;

public interface StaffTransferService {
    String add(StaffTransfer staffTransfer);
    String update(StaffTransfer staffTransfer);
    String delete(Long id);
    List<StaffTransfer> getAll();
    StaffTransfer getOne(Long id);

    List<StaffTransfer> findAllByReleasingManager(Employee employee);
    List<StaffTransfer> findAllByDepartmentToAssign(Department department);
    List<StaffTransfer> findAllByTransferringEmployee(Employee employee);
    List<StaffTransfer> findAllByHasBeenApproved(Boolean hasBeenApproved);
}
