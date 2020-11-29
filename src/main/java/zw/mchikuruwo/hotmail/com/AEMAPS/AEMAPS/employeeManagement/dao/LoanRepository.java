package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Loan;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.LoanApplication;


import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findAllByEmployee(Employee employee);

    List<Loan> findAllByLoanApplication(LoanApplication loanApplication);

    List<Loan> findAllByLoanApplicationAndEmployee(LoanApplication loanApplication, Employee employee);
}
