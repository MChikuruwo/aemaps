package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.stewardbank.hrautomationplatform.dao.LoanTypeRepository;
import zw.co.stewardbank.hrautomationplatform.models.LoanType;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LoanTypeServiceImpl implements LoanTypeService {

    private final LoanTypeRepository loanTypeRepository;

    @Autowired
    public LoanTypeServiceImpl(LoanTypeRepository loanTypeRepository) {
        this.loanTypeRepository = loanTypeRepository;
    }

    @Override
    public String add(LoanType loanType) {
        loanTypeRepository.save(loanType);
        return "Loan Type request has been successfully added";    }

    @Override
    public String update(LoanType loanType) {
        Optional<LoanType> detailsFromDatabase = loanTypeRepository.findById(loanType.getId());
        if (!detailsFromDatabase.isPresent()) throw new EntityNotFoundException("Loan Type  does not exist");
        // Use Loan type to pick one
        loanType.setType(detailsFromDatabase.get().getType());
        loanTypeRepository.save(loanType);
        return "Loan Type  with ID " + loanType.getId() + " has been updated";    }

    @Override
    public String delete(Integer id) {
        Optional<LoanType> detailsToDelete = loanTypeRepository.findById(id);
        if (!detailsToDelete.isPresent()){
            throw new EntityNotFoundException("Loan Type with ID " + id + " does not exist");
        }
        loanTypeRepository.deleteById(id);
        return "Loan Type request has been deleted successfully";    }

    @Override
    public List<LoanType> getAll() {
        List<LoanType> loanTypes = loanTypeRepository.findAll();
        if (loanTypes.isEmpty()){
            throw new EntityNotFoundException("Loan Types " + " not found");
        }
        return loanTypes;     }

    @Override
    public LoanType getOne(Integer id) {
        Optional<LoanType> loanType = loanTypeRepository.findById(id);
        if (!loanType.isPresent()){
            throw new EntityNotFoundException("Loan Type with the ID " + id + " does not exist");
        }
        return loanType.get();    }

    @Override
    public LoanType findByType(String type) {
        LoanType loanType = loanTypeRepository.findByType(type);
        if (loanType == null){
            throw new EntityNotFoundException("Loan Type  "
                    .concat(type).concat(" not found"));
        }
        return loanType;
    }
}
