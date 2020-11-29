package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.SpouseDetails;

import java.util.List;

public interface SpouseDetailsService {
    String add(SpouseDetails spouseDetails);
    String update(SpouseDetails spouseDetails);
    String delete(Long id);
    List<SpouseDetails> getAll();
    SpouseDetails getOne(Long id);
    SpouseDetails findByEmployee(Employee employee);
}
