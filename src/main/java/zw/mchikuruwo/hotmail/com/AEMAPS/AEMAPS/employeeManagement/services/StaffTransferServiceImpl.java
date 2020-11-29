package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.stewardbank.hrautomationplatform.dao.StaffTransferRepository;
import zw.co.stewardbank.hrautomationplatform.models.Department;
import zw.co.stewardbank.hrautomationplatform.models.Employee;
import zw.co.stewardbank.hrautomationplatform.models.StaffTransfer;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffTransferServiceImpl implements StaffTransferService {

    private final StaffTransferRepository staffTransferRepository;

    @Autowired
    public StaffTransferServiceImpl(StaffTransferRepository staffTransferRepository) {
        this.staffTransferRepository = staffTransferRepository;
    }

    @Override
    public String add(StaffTransfer staffTransfer) {
        staffTransferRepository.save(staffTransfer);
        return "Staff transfer request has been submitted";
    }

    @Override
    public String update(StaffTransfer staffTransfer) {
        Optional<StaffTransfer> detailsFromDatabase = staffTransferRepository.findById(staffTransfer.getId());
        if (!detailsFromDatabase.isPresent()) throw new EntityNotFoundException("Staff transfer request does not exist");
        // Carry date created timestamp
        staffTransfer.setDateCreated(detailsFromDatabase.get().getDateCreated());
        staffTransferRepository.save(staffTransfer);
        return "Staff transfer request with ID " + staffTransfer.getId() + " has been updated";
    }

    @Override
    public String delete(Long id) {
        Optional<StaffTransfer> detailsToDelete = staffTransferRepository.findById(id);
        if (!detailsToDelete.isPresent()){
            throw new EntityNotFoundException("Staff transfer request with ID " + id + " does not exist");
        }
        staffTransferRepository.deleteById(id);
        return "Staff transfer request has been deleted";

    }

    @Override
    public List<StaffTransfer> getAll() {
        List<StaffTransfer> staffTransfers = staffTransferRepository.findAll();
        if (staffTransfers.isEmpty()){
            throw new EntityNotFoundException("Staff transfer requests not found");
        }
        return staffTransfers;
    }

    @Override
    public StaffTransfer getOne(Long id) {
        Optional<StaffTransfer> staffTransfer = staffTransferRepository.findById(id);
        if (!staffTransfer.isPresent()){
            throw new EntityNotFoundException("Staff transfer request with the ID " + id + " does not exist");
        }
        return staffTransfer.get();
    }

    @Override
    public List<StaffTransfer> findAllByReleasingManager(Employee employee) {
        List<StaffTransfer> staffTransfers = staffTransferRepository.findAllByReleasingManager(employee);
        if (staffTransfers.isEmpty()){
            throw new EntityNotFoundException("Staff transfer requests under the releasing manager "
                    .concat(employee.getJobTitle().getTitleName()).concat(" not found"));
        }
        return staffTransfers;
    }

    @Override
    public List<StaffTransfer> findAllByDepartmentToAssign(Department department) {
        List<StaffTransfer> staffTransfers = staffTransferRepository.findAllByDepartmentToAssign(department);
        if (staffTransfers.isEmpty()){
            throw new EntityNotFoundException("Staff transfer requests with the target department "
                    .concat(department.getDepartment()).concat(" not found"));
        }
        return staffTransfers;
    }

    @Override
    public List<StaffTransfer> findAllByTransferringEmployee(Employee employee) {
        List<StaffTransfer> staffTransfers = staffTransferRepository.findAllByTransferringEmployee(employee);
        if (staffTransfers.isEmpty()){
            throw new EntityNotFoundException("Staff transfer requests from the employee "
                    .concat(employee.getName()).concat(" ").concat(employee.getSurname()).concat(" not found"));
        }
        return staffTransfers;
    }

    @Override
    public List<StaffTransfer> findAllByHasBeenApproved(Boolean hasBeenApproved) {
        return staffTransferRepository.findAllByHasBeenApproved(hasBeenApproved);
    }


}
