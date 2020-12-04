package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.AnnualActivityCalendar;


import java.util.List;

public interface AnnualActivityCalendarService {
    String add(AnnualActivityCalendar annualActivityCalendar);
    String update(AnnualActivityCalendar annualActivityCalendar);
    String delete(Long id);
    List<AnnualActivityCalendar> getAll();
    AnnualActivityCalendar getOne(Long id);
    AnnualActivityCalendar findByActivity(String activity);

}
