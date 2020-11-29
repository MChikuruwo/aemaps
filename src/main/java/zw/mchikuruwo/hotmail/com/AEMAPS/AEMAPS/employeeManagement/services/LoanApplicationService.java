package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;


import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.LoanApplication;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.LoanType;

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
