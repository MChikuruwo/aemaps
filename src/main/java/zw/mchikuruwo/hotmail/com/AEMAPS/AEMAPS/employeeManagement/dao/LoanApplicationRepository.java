package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.LoanApplication;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.LoanType;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    
    List<LoanApplication> findAllByEmployee(Employee employee);
    List<LoanApplication> findAllByLoanType(LoanType loanType);
    List<LoanApplication> findAllByHasBeenApproved(Boolean hasBeenApproved);
}
