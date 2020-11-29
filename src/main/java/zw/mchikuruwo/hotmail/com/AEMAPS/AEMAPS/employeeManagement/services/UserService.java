package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.User;

import java.util.List;

public interface UserService {
    String add(User user);
    String update(User user);
    String delete(Integer id);
    List<User> getAll();
    User getOne(Integer id);
    User authUser(String emailAddress, String password) throws Exception;

    User empCodeAuth(String employeeCode, String password) throws Exception;
    User findByEmailAddress(String emailAddress);
}
