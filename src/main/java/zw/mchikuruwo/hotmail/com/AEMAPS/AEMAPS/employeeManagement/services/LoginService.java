package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;



import zw.co.stewardbank.hrautomationplatform.models.Login;
import zw.co.stewardbank.hrautomationplatform.models.User;

import java.util.Date;
import java.util.List;

public interface LoginService {
    Login add(Login login);
    List<Login> getAll();
    Login getOne(Integer id);

    List<Login> findAllByUser(User user);
    List<Login> findAllByDate(Date date);
}
