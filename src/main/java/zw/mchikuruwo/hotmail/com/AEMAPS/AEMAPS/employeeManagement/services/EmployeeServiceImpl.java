package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao.EmployeeRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.exceptions.EmployeeAlreadyExistsException;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Employee;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.EmployeeStatus;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.JobTitle;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String add(Employee employee) {
        employeeRepository.save(employee);
        Optional<Employee> employeeFromDatabase = Optional.ofNullable(employeeRepository.findEmployeeByEmployeeCode(employee.getEmployeeCode()));
        if (employeeFromDatabase.isPresent()) throw new EmployeeAlreadyExistsException("Employee Already exists!");

        return "Employee has been successfully added.";
    }

    @Transactional
    @Override
    public String update(Employee employee) {
        Optional<Employee> employeeFromDatabase = employeeRepository.findById(employee.getId());
        if (!employeeFromDatabase.isPresent()) throw new EntityNotFoundException("Employee does not exist");
        // Carry date created timestamp
        employee.setDateCreated(employeeFromDatabase.get().getDateCreated());
        employeeRepository.save(employee);
        return "Employee with ID " + employee.getId() + " has been updated";
    }

    @Transactional
    @Override
    public String delete(Long id) {
        Optional<Employee> employeeToDelete = employeeRepository.findById(id);
        if (!employeeToDelete.isPresent()){
            throw new EntityNotFoundException("Employee with ID " + id + " does not exist");
        }
        employeeRepository.deleteById(id);
        return "Employee has been deleted";

    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()){
            throw new EntityNotFoundException("Employees not found");
        }
        return employees;
    }

    @Override
    public Employee getOne(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()){
            throw new EntityNotFoundException("Employee with the ID " + id + " does not exist");
        }
        return employee.get();
    }

    @Override
    public Employee findByUserId(Integer userId) {
        Employee employee = employeeRepository.findByUserId(userId);
        if (employee == null){
            throw new EntityNotFoundException("Employee with user id " + userId + " not found");
        }
        return employee;
    }

    @Override
    public Employee findByEmployeeCode(String employeeCode) {
        Employee employee = employeeRepository.findByEmployeeCode(employeeCode);
        if (employee == null){
            throw new EntityNotFoundException("Employee with code " + employeeCode + " not found");
        }
        return employee;
    }

    @Override
    public List<Employee> findAllByEmployeeStatus(EmployeeStatus employeeStatus) {
        List<Employee> employees = employeeRepository.findAllByEmployeeStatus(employeeStatus);
        if (employees.isEmpty()){
            throw new EntityNotFoundException("Employees with employee status ".concat(employeeStatus.getStatus()).concat(" not found"));
        }
        return employees;
    }

    @Override
    public List<Employee> findAllByJobTitle(JobTitle jobTitle) {
        List<Employee> employees = employeeRepository.findAllByJobTitle(jobTitle);
        if (employees.isEmpty()){
            throw new EntityNotFoundException("Employees with job title ".concat(jobTitle.getTitleName()).concat(" not found"));
        }
        return employees;
    }

    @Override
    public List<Employee> findAllByResidentialStatus(String residentialStatus) {
        List<Employee> employees = employeeRepository.findAllByResidentialStatus(residentialStatus);
        if (employees.isEmpty()){
            throw new EntityNotFoundException("Employees with residential status ".concat(residentialStatus).concat(" not found"));
        }
        return employees;
    }

    @Override
    public List<Employee> findAllByGender(String gender) {
        List<Employee> employees = employeeRepository.findAllByGender(gender);
        if (employees.isEmpty()){
            throw new EntityNotFoundException("Employees with gender ".concat(gender).concat(" not found"));
        }
        return employees;
    }


}
