package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;


import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Loan;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.LoanApplication;

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
