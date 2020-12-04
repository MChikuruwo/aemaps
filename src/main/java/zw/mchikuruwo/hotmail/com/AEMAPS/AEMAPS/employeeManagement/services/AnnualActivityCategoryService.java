package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.AnnualActivityCategory;

import java.util.List;

public interface AnnualActivityCategoryService {
    List<AnnualActivityCategory> getAll();
    AnnualActivityCategory getOne(Long id);
    AnnualActivityCategory findByCategory(String category);
}
