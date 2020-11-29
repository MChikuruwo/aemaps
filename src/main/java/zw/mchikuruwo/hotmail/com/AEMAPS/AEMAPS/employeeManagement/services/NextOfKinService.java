package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;


import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.NextOfKin;

import java.util.List;

public interface NextOfKinService {
    String add(NextOfKin nextOfKin);
    String update(NextOfKin nextOfKin);
    String delete(Long id);
    List<NextOfKin> getAll();
    NextOfKin getOne(Long id);
    NextOfKin findByEmployee(Employee employee);
}
