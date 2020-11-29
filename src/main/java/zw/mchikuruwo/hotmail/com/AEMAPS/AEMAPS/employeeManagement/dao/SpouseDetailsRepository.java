package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.SpouseDetails;

@Repository
public interface SpouseDetailsRepository extends JpaRepository<SpouseDetails, Long> {
    SpouseDetails findByEmployee(Employee employee);
}
