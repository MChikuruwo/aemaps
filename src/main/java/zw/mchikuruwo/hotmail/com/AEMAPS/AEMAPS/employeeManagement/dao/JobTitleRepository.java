package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.BusinessUnit;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Department;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.JobTitle;


import java.util.List;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {
    List<JobTitle> findAllByBusinessUnit(BusinessUnit businessUnit);
    List<JobTitle> findAllByDepartment(Department department);
    List<JobTitle> findAllByBusinessUnitAndDepartment(BusinessUnit businessUnit, Department department);
}
