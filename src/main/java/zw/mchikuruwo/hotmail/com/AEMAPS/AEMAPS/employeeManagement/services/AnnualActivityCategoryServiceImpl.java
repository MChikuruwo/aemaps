package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao.AnnualActivityCategoryRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.AnnualActivityCategory;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AnnualActivityCategoryServiceImpl implements AnnualActivityCategoryService {

    private final AnnualActivityCategoryRepository annualActivityCategoryRepository;

    @Autowired
    public AnnualActivityCategoryServiceImpl(AnnualActivityCategoryRepository annualActivityCategoryRepository) {
        this.annualActivityCategoryRepository = annualActivityCategoryRepository;
    }

    @Override
    public List<AnnualActivityCategory> getAll() {

        return annualActivityCategoryRepository.findAll();
    }

    @Override
    public AnnualActivityCategory getOne(Long id) {
        Optional<AnnualActivityCategory> category = annualActivityCategoryRepository.findById(id);
        if (!category.isPresent()){
            throw new EntityNotFoundException("Category not found!");
        }
        return category.get();
    }

    @Override
    public AnnualActivityCategory findByCategory(String name) {
        AnnualActivityCategory category = annualActivityCategoryRepository.findByCategory(name);
        if (category == null){
            throw new EntityNotFoundException("Category " + name + " not found");
        }
        return category;
    }

}
