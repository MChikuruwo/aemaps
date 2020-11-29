package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Login;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.User;


import java.util.Date;
import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    List<Login> findAllByUser(User user);
    List<Login> findAllByDate(Date date);
}
