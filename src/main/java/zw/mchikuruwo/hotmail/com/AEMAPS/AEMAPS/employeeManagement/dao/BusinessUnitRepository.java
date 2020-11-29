package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.stewardbank.hrautomationplatform.models.BusinessUnit;

@Repository
public interface BusinessUnitRepository extends JpaRepository<BusinessUnit, Integer> {
    BusinessUnit findByBusinessUnit(String businessUnit);
}
