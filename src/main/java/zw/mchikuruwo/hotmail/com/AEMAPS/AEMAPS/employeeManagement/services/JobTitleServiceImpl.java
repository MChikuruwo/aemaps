package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.stewardbank.hrautomationplatform.dao.JobTitleRepository;
import zw.co.stewardbank.hrautomationplatform.models.BusinessUnit;
import zw.co.stewardbank.hrautomationplatform.models.Department;
import zw.co.stewardbank.hrautomationplatform.models.JobTitle;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JobTitleServiceImpl implements JobTitleService {

    private final JobTitleRepository jobTitleRepository;

    @Autowired
    public JobTitleServiceImpl(JobTitleRepository jobTitleRepository) {
        this.jobTitleRepository = jobTitleRepository;
    }

    @Override
    public String add(JobTitle jobTitle) {
        jobTitleRepository.save(jobTitle);
        return "Job title has been created";
    }

    @Transactional
    @Override
    public String update(JobTitle jobTitle) {
        Optional<JobTitle> titleFromDatabase = jobTitleRepository.findById(jobTitle.getId());
        if (!titleFromDatabase.isPresent()) throw new EntityNotFoundException("Job title does not exist");
        jobTitleRepository.save(jobTitle);
        return "Job title with ID " + jobTitle.getId() + " has been updated";
    }

    @Transactional
    @Override
    public String delete(Long id) {
        Optional<JobTitle> titleToDelete = jobTitleRepository.findById(id);
        if (!titleToDelete.isPresent()){
            throw new EntityNotFoundException("Job title with ID " + id + " does not exist");
        }
        jobTitleRepository.deleteById(id);
        return "Job title has been deleted";

    }

    @Override
    public List<JobTitle> getAll() {
        List<JobTitle> jobTitles = jobTitleRepository.findAll();
        if (jobTitles.isEmpty()){
            throw new EntityNotFoundException("Job titles not found");
        }
        return jobTitles;
    }

    @Override
    public JobTitle getOne(Long id) {
        Optional<JobTitle> jobTitle = jobTitleRepository.findById(id);
        if (!jobTitle.isPresent()){
            throw new EntityNotFoundException("Job title with the ID " + id + " does not exist");
        }
        return jobTitle.get();
    }

    @Override
    public List<JobTitle> findAllByBusinessUnit(BusinessUnit businessUnit) {
        List<JobTitle> jobTitles = jobTitleRepository.findAllByBusinessUnit(businessUnit);
        if (jobTitles.isEmpty()){
            throw new EntityNotFoundException("Job titles under the Business unit "
                    .concat(businessUnit.getBusinessUnit()).concat(" not found"));
        }
        return jobTitles;
    }

    @Override
    public List<JobTitle> findAllByDepartment(Department department) {
        List<JobTitle> jobTitles = jobTitleRepository.findAllByDepartment(department);
        if (jobTitles.isEmpty()){
            throw new EntityNotFoundException("Job titles under the Department "
                    .concat(department.getDepartment()).concat(" not found"));
        }
        return jobTitles;
    }

    @Override
    public List<JobTitle> findAllByBusinessUnitAndDepartment(BusinessUnit businessUnit,
                                                             Department department) {
        List<JobTitle> jobTitles = jobTitleRepository.findAllByBusinessUnitAndDepartment(businessUnit, department);
        if (jobTitles.isEmpty()){
            throw new EntityNotFoundException("Job titles under the Business unit "
                    .concat(businessUnit.getBusinessUnit()).concat(" and Department ")
                    .concat(department.getDepartment()).concat(" not found"));
        }
        return jobTitles;
    }

    @Override
    public List<JobTitle> findAllByReportingTo(Integer reportingToId) {
        List<JobTitle> jobTitles = jobTitleRepository.findAllByReportingTo(reportingToId);
        if (jobTitles.isEmpty()){
            throw new EntityNotFoundException("Job titles reporting to "
                    .concat(jobTitleRepository.getOne(Long.valueOf(reportingToId)).getTitleName()).concat(" not found"));
        }
        return jobTitles;
    }


}
