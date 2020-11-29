package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao.SalaryAdvanceRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.SalaryAdvance;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.SalaryAdvanceApplication;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalaryAdvanceServiceImpl implements SalaryAdvanceService {

    private final SalaryAdvanceRepository salaryAdvanceRepository;

    @Autowired
    public SalaryAdvanceServiceImpl(SalaryAdvanceRepository salaryAdvanceRepository) {
        this.salaryAdvanceRepository = salaryAdvanceRepository;
    }

    @Override
    public String add(SalaryAdvance salaryAdvance) {
        salaryAdvanceRepository.save(salaryAdvance);
        return "Salary advance request has been submitted";     }

    @Override
    public String update(SalaryAdvance salaryAdvance) {
        Optional<SalaryAdvance> titleFromDatabase = salaryAdvanceRepository.findById(salaryAdvance.getId());
        if (!titleFromDatabase.isPresent()) throw new EntityNotFoundException("Salary Advance request does not exist");
        salaryAdvanceRepository.save(salaryAdvance);
        return "Salary advance request with ID " + salaryAdvance.getId() + " has been updated";    }

    @Override
    public String delete(Long id) {
        Optional<SalaryAdvance> salaryAdvanceToDelete = salaryAdvanceRepository.findById(id);
        if (!salaryAdvanceToDelete.isPresent()){
            throw new EntityNotFoundException("Salary advance Enquiry with ID " + id + " does not exist");
        }
        salaryAdvanceRepository.deleteById(id);
        return "Salary Advance has been successfully deleted";
    }

    @Override
    public List<SalaryAdvance> getAll() {

        List<SalaryAdvance> salaryAdvances = salaryAdvanceRepository.findAll();
        if (salaryAdvances.isEmpty()){
            throw new EntityNotFoundException("Salary advance enquiries not found");
        }
        return salaryAdvances;      }

    @Override
    public SalaryAdvance getOne(Long id) {

        Optional<SalaryAdvance> salaryAdvance = salaryAdvanceRepository.findById(id);
        if (!salaryAdvance.isPresent()){
            throw new EntityNotFoundException("Salary advance request with the ID " + id + " does not exist");
        }
        return salaryAdvance.get();     }

    @Override
    public List<SalaryAdvance> findAllByEmployee(Employee employee)
    {
        List<SalaryAdvance> salaryAdvances = salaryAdvanceRepository.findAllByEmployee(employee);
        if (salaryAdvances.isEmpty()){
            throw new EntityNotFoundException("Loan requests under the Employee"
                    .concat(employee.getEmployeeCode()).concat(" not found"));
        }
        return salaryAdvances;      }

    @Override
    public List<SalaryAdvance> findAllBySalaryAdvanceApplication(SalaryAdvanceApplication salaryAdvanceApplication) {
        List<SalaryAdvance> salaryAdvances = salaryAdvanceRepository.findAllBySalaryAdvanceApplication(salaryAdvanceApplication);
        if (salaryAdvances.isEmpty()){
            throw new EntityNotFoundException("Salary advance requests under the Salary advance application"
                    .concat(salaryAdvanceApplication.getPurpose()).concat(" not found"));
        }
        return salaryAdvances;    }

    @Override
    public List<SalaryAdvance> findAllBySalaryAdvanceApplicationAndEmployee(SalaryAdvanceApplication salaryAdvanceApplication, Employee employee) {
        List<SalaryAdvance> salaryAdvance = salaryAdvanceRepository.findAllBySalaryAdvanceApplicationAndEmployee(salaryAdvanceApplication, employee);
        if (salaryAdvance.isEmpty()){
            throw new EntityNotFoundException("Salary advance under the Salary advance application :"
                    .concat(salaryAdvanceApplication.getPurpose()).concat(" and employee ")
                    .concat(employee.getName().concat(employee.getSurname())).concat(" not found"));
        }
        return salaryAdvance;    }
}
