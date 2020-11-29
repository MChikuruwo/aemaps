package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.LoanApplication;
import zw.co.stewardbank.hrautomationplatform.models.LoanType;

import java.util.List;

public interface LoanApplicationService {
    String add(LoanApplication loanApplication);
    String update(LoanApplication loanApplication);
    String delete(Long id);
    List<LoanApplication> getAll();
    LoanApplication getOne(Long id);

    List<LoanApplication> findAllByEmployeeCode(Employee employee);
    List<LoanApplication> findAllByLoanType(LoanType loanType);
    List<LoanApplication> findAllByHasBeenApproved(Boolean hasBeenApproved);
}
