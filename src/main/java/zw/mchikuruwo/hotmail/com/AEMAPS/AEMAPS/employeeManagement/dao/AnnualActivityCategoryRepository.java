package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.AnnualActivityCategory;

public interface AnnualActivityCategoryRepository extends JpaRepository<AnnualActivityCategory,Long> {
    AnnualActivityCategory findByCategory(String category);
}
