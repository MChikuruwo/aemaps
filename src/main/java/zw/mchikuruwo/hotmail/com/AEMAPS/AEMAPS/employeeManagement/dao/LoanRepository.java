package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.Loan;
import zw.co.stewardbank.hrautomationplatform.models.LoanApplication;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findAllByEmployee(Employee employee);

    List<Loan> findAllByLoanApplication(LoanApplication loanApplication);

    List<Loan> findAllByLoanApplicationAndEmployee(LoanApplication loanApplication, Employee employee);
}
