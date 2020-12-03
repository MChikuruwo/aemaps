package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.AnnualActivityCalendar;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Notices;

import java.util.List;

public interface AnnualActivityCalendarService {
    List<AnnualActivityCalendar> getAll();
    AnnualActivityCalendar getOne(Long id);
    AnnualActivityCalendar findByActivity(String activity);
}
