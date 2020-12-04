package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.AnnualActivityCalendar;


import java.util.List;

public interface AnnualActivityCalendarService {
    String add(AnnualActivityCalendar annualActivityCalendar);
    List<AnnualActivityCalendar> getAll();
    AnnualActivityCalendar getOne(Long id);
    AnnualActivityCalendar findByActivity(String activity);

}
