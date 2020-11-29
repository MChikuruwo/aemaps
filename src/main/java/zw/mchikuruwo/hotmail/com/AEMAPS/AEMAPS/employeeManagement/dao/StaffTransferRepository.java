package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.stewardbank.hrautomationplatform.models.Department;
import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.StaffTransfer;

import java.util.List;

@Repository
public interface StaffTransferRepository extends JpaRepository<StaffTransfer, Long> {
    List<StaffTransfer> findAllByReleasingManager(Employee employee);
    List<StaffTransfer> findAllByDepartmentToAssign(Department department);
    List<StaffTransfer> findAllByTransferringEmployee(Employee employee);
    List<StaffTransfer> findAllByHasBeenApproved(Boolean hasBeenApproved);
}
