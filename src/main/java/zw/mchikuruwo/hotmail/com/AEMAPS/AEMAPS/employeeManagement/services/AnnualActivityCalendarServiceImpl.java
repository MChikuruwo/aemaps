package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao.AnnualActivityCalendarRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao.NoticesRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.AnnualActivityCalendar;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Notices;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AnnualActivityCalendarServiceImpl implements AnnualActivityCalendarService {

    private final AnnualActivityCalendarRepository annualActivityCalendarRepository;

    @Autowired
    public AnnualActivityCalendarServiceImpl(AnnualActivityCalendarRepository annualActivityCalendarRepository) {
        this.annualActivityCalendarRepository = annualActivityCalendarRepository;
    }

    @Override
    public List<AnnualActivityCalendar> getAll() {
        return annualActivityCalendarRepository.findAll();
    }

    @Override
    public AnnualActivityCalendar getOne(Long id) {
        Optional<AnnualActivityCalendar> annualActivityCalendar = annualActivityCalendarRepository.findById(id);
        if (!annualActivityCalendar.isPresent()){
            throw new EntityNotFoundException("Activity not found!");
        }
        return annualActivityCalendar.get();
    }

    @Override
    public AnnualActivityCalendar findByActivity(String activity) {
        AnnualActivityCalendar annualActivityCalendar = annualActivityCalendarRepository.findByActivity(activity);
        if (annualActivityCalendar == null){
            throw new EntityNotFoundException("Activity " + activity + " not found");
        }
        return annualActivityCalendar;
    }


}

