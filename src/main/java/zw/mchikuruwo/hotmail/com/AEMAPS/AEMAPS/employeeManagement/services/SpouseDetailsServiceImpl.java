package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.stewardbank.hrautomationplatform.dao.SpouseDetailsRepository;
import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.SpouseDetails;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpouseDetailsServiceImpl implements SpouseDetailsService {

    private final SpouseDetailsRepository spouseDetailsRepository;

    @Autowired
    public SpouseDetailsServiceImpl(SpouseDetailsRepository spouseDetailsRepository) {
        this.spouseDetailsRepository = spouseDetailsRepository;
    }

    @Override
    public String add(SpouseDetails spouseDetails) {
        spouseDetailsRepository.save(spouseDetails);
        return "Employee spouse details have been added to system";
    }

    @Override
    public String update(SpouseDetails spouseDetails) {
        Optional<SpouseDetails> detailsFromDatabase = spouseDetailsRepository.findById(spouseDetails.getId());
        if (!detailsFromDatabase.isPresent()) throw new EntityNotFoundException("Employee spouse details do not exist");
        // Carry date created timestamp
        spouseDetails.setDateCreated(detailsFromDatabase.get().getDateCreated());
        spouseDetailsRepository.save(spouseDetails);
        return "Employee spouse details with ID " + spouseDetails.getId() + " has been updated";
    }

    @Override
    public String delete(Long id) {
        Optional<SpouseDetails> detailsToDelete = spouseDetailsRepository.findById(id);
        if (!detailsToDelete.isPresent()){
            throw new EntityNotFoundException("Employee spouse details with ID " + id + " does not exist");
        }
        spouseDetailsRepository.deleteById(id);
        return "Employee spouse details have been deleted";

    }

    @Override
    public List<SpouseDetails> getAll() {
        List<SpouseDetails> spouseDetails = spouseDetailsRepository.findAll();
        if (spouseDetails.isEmpty()){
            throw new EntityNotFoundException("Employees spouse details not found");
        }
        return spouseDetails;
    }

    @Override
    public SpouseDetails getOne(Long id) {
        Optional<SpouseDetails> spouseDetails = spouseDetailsRepository.findById(id);
        if (!spouseDetails.isPresent()){
            throw new EntityNotFoundException("Employee with the ID " + id + " does not exist");
        }
        return spouseDetails.get();
    }

    @Override
    public SpouseDetails findByEmployee(Employee employee) {
        SpouseDetails spouseDetails = spouseDetailsRepository.findByEmployee(employee);
        if (spouseDetails == null){
            throw new EntityNotFoundException("Spouse details for employee with code "
                    .concat(employee.getEmployeeCode()).concat(" not found"));
        }
        return spouseDetails;
    }


}
