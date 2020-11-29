package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.stewardbank.hrautomationplatform.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByDepartment(String department);
}
