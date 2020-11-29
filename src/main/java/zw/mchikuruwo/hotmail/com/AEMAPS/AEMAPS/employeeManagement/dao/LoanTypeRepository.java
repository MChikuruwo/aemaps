package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.stewardbank.hrautomationplatform.models.LoanType;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType,Integer> {


    LoanType findByType(String type);
}
