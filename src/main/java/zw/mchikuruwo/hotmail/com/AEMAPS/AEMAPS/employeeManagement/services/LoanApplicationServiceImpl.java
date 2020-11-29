package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.stewardbank.hrautomationplatform.dao.LoanApplicationRepository;
import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.LoanApplication;
import zw.co.stewardbank.hrautomationplatform.models.LoanType;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;

    @Autowired
    public LoanApplicationServiceImpl(LoanApplicationRepository loanApplicationRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
    }


    @Override
    public String add(LoanApplication loanApplication) {
        loanApplicationRepository.save(loanApplication);
        return "Loan Application request has been submitted";
    }

    @Override
    public String update(LoanApplication loanApplication) {
        Optional<LoanApplication> detailsFromDatabase = loanApplicationRepository.findById(loanApplication.getId());
        if (!detailsFromDatabase.isPresent()) throw new EntityNotFoundException("Loan Application request does not exist");
        // Carry date created timestamp
        loanApplication.setDateCreated(detailsFromDatabase.get().getDateCreated());
        loanApplicationRepository.save(loanApplication);
        return "Loan Application request with ID " + loanApplication.getId() + " has been updated";    }

    @Override
    public String delete(Long id) {
        Optional<LoanApplication> detailsToDelete = loanApplicationRepository.findById(id);
        if (!detailsToDelete.isPresent()){
            throw new EntityNotFoundException("Loan Application request with ID " + id + " does not exist");
        }
        loanApplicationRepository.deleteById(id);
        return "Loan Application request has been deleted";    }

    @Override
    public List<LoanApplication> getAll() {
        List<LoanApplication> loanApplications = loanApplicationRepository.findAll();
        if (loanApplications.isEmpty()){
            throw new EntityNotFoundException("Loan Application requests not found");
        }
        return loanApplications;    }

    @Override
    public LoanApplication getOne(Long id) {
        Optional<LoanApplication> loanApplication = loanApplicationRepository.findById(id);
        if (!loanApplication.isPresent()){
            throw new EntityNotFoundException("Loan Application request with the ID " + id + " does not exist");
        }
        return loanApplication.get();    }

    @Override
    public List<LoanApplication> findAllByEmployeeCode(Employee employee) {
        List<LoanApplication> loanApplications = loanApplicationRepository.findAllByEmployee(employee);
        if (loanApplications.isEmpty()){
            throw new EntityNotFoundException("Loan Application requests for employee:" .concat(employee.getName().concat(employee.getSurname())).concat("Employee Code:")
                    .concat(employee.getEmployeeCode()).concat(" not found"));
        }
        return loanApplications;    }

    @Override
    public List<LoanApplication> findAllByLoanType(LoanType loanType) {
            List<LoanApplication> loanApplications = loanApplicationRepository.findAllByLoanType(loanType);
            if (loanApplications.isEmpty()){
                throw new EntityNotFoundException("Loan Application requests from the employee "
                        .concat(loanType.getType()).concat(" not found"));
            }
            return loanApplications;    }

    @Override
    public List<LoanApplication> findAllByHasBeenApproved(Boolean hasBeenApproved) {
        return loanApplicationRepository.findAllByHasBeenApproved(hasBeenApproved);
    }
}
