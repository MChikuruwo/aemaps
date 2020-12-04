package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Notices;

import java.util.List;

public interface NoticesService {
    String add(Notices notices);
    List<Notices> getAll();
    Notices getOne(Long id);
    Notices findByTitle(String title);
}
