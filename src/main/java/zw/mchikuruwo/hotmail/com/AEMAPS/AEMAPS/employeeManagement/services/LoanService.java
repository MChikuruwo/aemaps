package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.Loan;
import zw.co.stewardbank.hrautomationplatform.models.LoanApplication;

import java.util.List;

public interface LoanService {
    String add(Loan loan);
    String update(Loan loan);
    String delete(Long id);
    List<Loan> getAll();
    Loan getOne(Long id);

    List<Loan> findAllByEmployee(Employee employee);
    List<Loan> findAllByLoanApplication(LoanApplication loanApplication);
    List<Loan> findAllByLoanApplicationAndEmployee(LoanApplication loanApplication, Employee employee);

}
