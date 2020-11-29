package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao.LoanRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Loan;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.LoanApplication;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoanServiceImpl implements LoanService{

    private final LoanRepository loanRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }


    @Override
    public String add(Loan loan) {
        loanRepository.save(loan);
        return "Loan enquiry request has been submitted";    }

    @Override
    public String update(Loan loan) {
        Optional<Loan> titleFromDatabase = loanRepository.findById(loan.getId());
        if (!titleFromDatabase.isPresent()) throw new EntityNotFoundException("Loan request does not exist");
        loanRepository.save(loan);
        return "Loan request with ID " + loan.getId() + " has been updated";
    }

    @Override
    public String delete(Long id) {
            Optional<Loan> loanToDelete = loanRepository.findById(id);
            if (!loanToDelete.isPresent()){
                throw new EntityNotFoundException("Loan Enquiry with ID " + id + " does not exist");
            }
            loanRepository.deleteById(id);
            return "Loan Enquiry has been successfully deleted";    }

    @Override
    public List<Loan> getAll() {
        List<Loan> loans = loanRepository.findAll();
        if (loans.isEmpty()){
            throw new EntityNotFoundException("Loan enquiries not found");
        }
        return loans;    }

    @Override
    public Loan getOne(Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        if (!loan.isPresent()){
            throw new EntityNotFoundException("Loan request with the ID " + id + " does not exist");
        }
            return loan.get();    }

    @Override
    public List<Loan> findAllByEmployee(Employee employee) {
        List<Loan> loans = loanRepository.findAllByEmployee(employee);
        if (loans.isEmpty()){
            throw new EntityNotFoundException("Loan requests under the Employee"
                    .concat(employee.getEmployeeCode()).concat(" not found"));
        }
        return loans;    }

    @Override
    public List<Loan> findAllByLoanApplication(LoanApplication loanApplication) {
        List<Loan> loans = loanRepository.findAllByLoanApplication(loanApplication);
        if (loans.isEmpty()){
            throw new EntityNotFoundException("Loan requests under the Loan application"
                    .concat(loanApplication.getLoanPurpose()).concat(" not found"));
        }
        return loans;    }

    @Override
    public List<Loan> findAllByLoanApplicationAndEmployee(LoanApplication loanApplication, Employee employee) {
        List<Loan> loans = loanRepository.findAllByLoanApplicationAndEmployee(loanApplication, employee);
        if (loans.isEmpty()){
            throw new EntityNotFoundException("Loans under the Loan application "
                    .concat(loanApplication.getLoanPurpose()).concat(" and employee ")
                    .concat(employee.getEmployeeCode()).concat(" not found"));
        }
        return loans;
    }
}

