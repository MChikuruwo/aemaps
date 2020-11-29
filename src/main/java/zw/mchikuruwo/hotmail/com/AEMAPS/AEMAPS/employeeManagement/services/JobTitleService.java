package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;


import zw.co.stewardbank.hrautomationplatform.models.BusinessUnit;
import zw.co.stewardbank.hrautomationplatform.models.Department;
import zw.co.stewardbank.hrautomationplatform.models.JobTitle;

import java.util.List;

public interface JobTitleService {
    String add(JobTitle jobTitle);
    String update(JobTitle jobTitle);
    String delete(Long id);
    List<JobTitle> getAll();
    JobTitle getOne(Long id);

    List<JobTitle> findAllByBusinessUnit(BusinessUnit businessUnit);
    List<JobTitle> findAllByDepartment(Department department);
    List<JobTitle> findAllByBusinessUnitAndDepartment(BusinessUnit businessUnit, Department department);
    List<JobTitle> findAllByReportingTo(Integer reportingToId);
}
