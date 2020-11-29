package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;




import zw.co.stewardbank.hrautomationplatform.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getOne(Integer id);
    Role findByName(String name);
}
