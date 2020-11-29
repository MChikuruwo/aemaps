package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.stewardbank.hrautomationplatform.dao.DelegationOfDutyRepository;
import zw.co.stewardbank.hrautomationplatform.models.DelegationOfDuty;
import zw.co.stewardbank.hrautomationplatform.models.Employee;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DelegationOfDutyServiceImpl  implements DelegationOfDutyService{

    private final DelegationOfDutyRepository delegationOfDutyRepository;

    @Autowired
    public DelegationOfDutyServiceImpl(DelegationOfDutyRepository delegationOfDutyRepository) {
        this.delegationOfDutyRepository = delegationOfDutyRepository;
    }

    @Override
    public String add(DelegationOfDuty delegationOfDuty) {
        delegationOfDutyRepository.save(delegationOfDuty);
        return "Delegation of duty request has been submitted";
    }

    @Override
    public String update(DelegationOfDuty delegationOfDuty) {
        Optional<DelegationOfDuty> detailsFromDatabase = delegationOfDutyRepository.findById(delegationOfDuty.getId());
        if (!detailsFromDatabase.isPresent()) throw new EntityNotFoundException("Delegation of duty  request does not exist");
        // Carry date created timestamp
        delegationOfDuty.setDateCreated(detailsFromDatabase.get().getDateCreated());
        delegationOfDutyRepository.save(delegationOfDuty);
        return "Delegation of duty  request with ID " + delegationOfDuty.getId() + " has been updated";    }

    @Override
    public String delete(Long id) {
        Optional<DelegationOfDuty> detailsToDelete = delegationOfDutyRepository.findById(id);
        if (!detailsToDelete.isPresent()){
            throw new EntityNotFoundException("Delegation of duty  request with ID " + id + " does not exist");
        }
        delegationOfDutyRepository.deleteById(id);
        return "Delegation of duty  request has been deleted";    }

    @Override
    public List<DelegationOfDuty> getAll() {
        List<DelegationOfDuty> delegationOfDuties = delegationOfDutyRepository.findAll();
        if (delegationOfDuties.isEmpty()){
            throw new EntityNotFoundException("Delegation of duty  requests not found");
        }
        return delegationOfDuties;    }

    @Override
    public DelegationOfDuty getOne(Long id) {
        Optional<DelegationOfDuty> delegationOfDuty = delegationOfDutyRepository.findById(id);
        if (!delegationOfDuty.isPresent()){
            throw new EntityNotFoundException("Delegation of duty  request with the ID " + id + " does not exist");
        }
        return delegationOfDuty.get();    }

    @Override
    public List<DelegationOfDuty> findAllByAssignedEmployee(Employee employee) {
        List<DelegationOfDuty> delegationOfDuties = delegationOfDutyRepository.findAllByAssignedEmployee(employee);
        if (delegationOfDuties.isEmpty()){
            throw new EntityNotFoundException("Delegation of  duty requests to employee: " .concat(employee.getName().concat(employee.getSurname())).concat("with Title:")
                    .concat(employee.getJobTitle().getTitleName()).concat(" not found"));
        }
        return delegationOfDuties;    }

    @Override
    public List<DelegationOfDuty> findAllByAssigningManager(Employee manager) {
        List<DelegationOfDuty> delegationOfDuties = delegationOfDutyRepository.findAllByAssigningManager(manager);
        if (delegationOfDuties.isEmpty()){
            throw new EntityNotFoundException("Delegation of duty  requests from "
                    .concat(manager.getName()).concat(" ").concat(manager.getSurname()).concat(" not found"));
        }
        return delegationOfDuties;    }

    @Override
    public List<DelegationOfDuty> findAllByAssigningManagerAndAssignedEmployee(Employee manager, Employee employee) {
        List<DelegationOfDuty> delegationOfDuties = delegationOfDutyRepository.findAllByAssigningManagerAndAssignedEmployee(manager, employee);
        if (delegationOfDuties.isEmpty()){
            throw new EntityNotFoundException("Delegation of duty request by manager:"
                    .concat(manager.getName().concat(manager.getSurname())).concat(" to employee:")
                    .concat(employee.getName().concat(employee.getSurname())).concat(" not found"));
        }
        return delegationOfDuties;    }
}
