package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.stewardbank.hrautomationplatform.dao.LoginRepository;
import zw.co.stewardbank.hrautomationplatform.models.Login;
import zw.co.stewardbank.hrautomationplatform.models.User;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Login add(Login login) {
        return loginRepository.save(login);
    }

    @Override
    public List<Login> getAll() {
        List<Login> loginList = loginRepository.findAll();
        if (loginList.isEmpty()){
            throw new EntityNotFoundException("No login entries found");
        }
        return loginList;
    }

    @Override
    public Login getOne(Integer id) {
        Optional<Login> login = loginRepository.findById(id);
        if (!login.isPresent()){
            throw new EntityNotFoundException("Login with ID " + id + " not found");
        }
        return login.get();
    }

    @Override
    public List<Login> findAllByUser(User user) {
        List<Login> loginList = loginRepository.findAllByUser(user);
        if (loginList.isEmpty()){
            throw new EntityNotFoundException("Login entries by user " + user.getEmailAddress() + " not found");
        }
        return loginList;
    }

    @Override
    public List<Login> findAllByDate(Date date) {
        List<Login> loginList = loginRepository.findAllByDate(date);
        if (loginList.isEmpty()){
            throw new EntityNotFoundException("No login entries found for the date " + date.toString());
        }
        return loginList;
    }
}
