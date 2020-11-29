package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao.SalaryAdvanceApplicationRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.SalaryAdvanceApplication;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalaryAdvanceApplicationServiceImpl implements SalaryAdvanceApplicationService {

    private final SalaryAdvanceApplicationRepository salaryAdvanceApplicationRepository;

    @Autowired
    public SalaryAdvanceApplicationServiceImpl(SalaryAdvanceApplicationRepository salaryAdvanceApplicationRepository) {
        this.salaryAdvanceApplicationRepository = salaryAdvanceApplicationRepository;
    }

    @Override
    public String add(SalaryAdvanceApplication salaryAdvanceApplication) {
        salaryAdvanceApplicationRepository.save(salaryAdvanceApplication);
        return "Salary Advance application  request has been submitted";    }

    @Override
    public String update(SalaryAdvanceApplication salaryAdvanceApplication) {
        Optional<SalaryAdvanceApplication> detailsFromDatabase = salaryAdvanceApplicationRepository.findById(salaryAdvanceApplication.getId());
        if (!detailsFromDatabase.isPresent()) throw new EntityNotFoundException("Salary advance application  request does not exist");
        // Carry date created timestamp
        salaryAdvanceApplication.setDateCreated(detailsFromDatabase.get().getDateCreated());
        salaryAdvanceApplicationRepository.save(salaryAdvanceApplication);
        return "Salary advance application  request with ID " + salaryAdvanceApplication.getId() + " has been updated";    }

    @Override
    public String delete(Long id) {
        Optional<SalaryAdvanceApplication> detailsToDelete = salaryAdvanceApplicationRepository.findById(id);
        if (!detailsToDelete.isPresent()){
            throw new EntityNotFoundException("Salary advance application request with ID " + id + " does not exist");
        }
        salaryAdvanceApplicationRepository.deleteById(id);
        return "Salary advance application  request has been deleted";    }

    @Override
    public List<SalaryAdvanceApplication> getAll() {
        List<SalaryAdvanceApplication> salaryAdvanceApplications = salaryAdvanceApplicationRepository.findAll();
        if (salaryAdvanceApplications.isEmpty()){
            throw new EntityNotFoundException("Salary advance application requests not found");
        }
        return salaryAdvanceApplications;    }

    @Override
    public SalaryAdvanceApplication getOne(Long id) {
        Optional<SalaryAdvanceApplication> salaryAdvanceApplication = salaryAdvanceApplicationRepository.findById(id);
        if (!salaryAdvanceApplication.isPresent()){
            throw new EntityNotFoundException("Salary advance application request with the ID " + id + " does not exist");
        }
        return salaryAdvanceApplication.get();    }

    @Override
    public List<SalaryAdvanceApplication> findAllByEmployeeCode(Employee employee) {
        List<SalaryAdvanceApplication> salaryAdvanceApplications = salaryAdvanceApplicationRepository.findAllByEmployee(employee);
        if (salaryAdvanceApplications.isEmpty()){
            throw new EntityNotFoundException("Salary advance application requests under employee :" .concat(employee.getName().concat(employee.getSurname())).concat("Employee Code:")
                    .concat(employee.getEmployeeCode()).concat(" not found"));
        }
        return salaryAdvanceApplications;    }

    @Override
    public List<SalaryAdvanceApplication> findAllBySignOffManager(Employee employee) {
        List<SalaryAdvanceApplication> salaryAdvanceApplications = salaryAdvanceApplicationRepository.findAllByReceivingManager(employee);
        if (salaryAdvanceApplications.isEmpty()){
            throw new EntityNotFoundException("Salary advance application requests under the releasing manager: "
                    .concat(employee.getJobTitle().getTitleName()).concat(" not found"));
        }
        return salaryAdvanceApplications;    }

    @Override
    public List<SalaryAdvanceApplication> findAllByHasBeenApproved(Boolean hasBeenApproved) {
        return salaryAdvanceApplicationRepository.findAllByHasBeenApproved(hasBeenApproved);
    }
}
