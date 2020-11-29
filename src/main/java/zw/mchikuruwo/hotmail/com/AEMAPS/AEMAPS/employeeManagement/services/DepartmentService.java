package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import zw.co.stewardbank.hrautomationplatform.models.Department;

import java.util.List;

public interface DepartmentService {
    String add(Department department);
    String update(Department department);
    String delete(Integer id);
    List<Department> getAll();
    Department getOne(Integer id);
    Department findByDepartment(String department);
}
