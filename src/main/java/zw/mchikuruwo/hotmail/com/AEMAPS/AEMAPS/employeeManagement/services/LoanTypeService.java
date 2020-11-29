package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;


import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.LoanType;

import java.util.List;


public interface LoanTypeService {
    String add(LoanType loanType);
    String update(LoanType loanType);
    String delete(Integer id);
    List<LoanType> getAll();
    LoanType getOne(Integer id);


    LoanType findByType(String loanType);
}
