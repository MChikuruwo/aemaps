package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;


import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.BusinessUnit;

import java.util.List;

public interface BusinessUnitService {
    String add(BusinessUnit businessUnit);
    String update(BusinessUnit businessUnit);
    String delete(Integer id);
    List<BusinessUnit> getAll();
    BusinessUnit getOne(Integer id);
    BusinessUnit findByBusinessUnit(String businessUnit);
}
