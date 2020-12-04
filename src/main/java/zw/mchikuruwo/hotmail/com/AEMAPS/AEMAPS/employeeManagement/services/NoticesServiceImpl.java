package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dao.NoticesRepository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.models.Notices;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class NoticesServiceImpl implements NoticesService{

    private final NoticesRepository noticesRepository;

    @Autowired
    public NoticesServiceImpl(NoticesRepository noticesRepository) {
        this.noticesRepository = noticesRepository;
    }

    @Override
    public String add(Notices notices) {
        noticesRepository.save(notices);
        return "Notice has been successfully added";
    }

    @Override
    public List<Notices> getAll() {
        return noticesRepository.findAll();
    }

    @Override
    public Notices getOne(Long id) {
        Optional<Notices> notice = noticesRepository.findById(id);
        if (!notice.isPresent()){
            throw new EntityNotFoundException("Notice not found!");
        }
        return notice.get();
    }

    @Override
    public Notices findByTitle(String title) {
        Notices notices = noticesRepository.findByTitle(title);
        if (notices == null){
            throw new EntityNotFoundException("Title " + title + " not found");
        }
        return notices;
    }


}

