package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.AnnualActivityCalendar;

public interface AnnualActivityCalendarRepository extends JpaRepository<AnnualActivityCalendar,Long> {
    AnnualActivityCalendar findByActivity(String activity);
}
