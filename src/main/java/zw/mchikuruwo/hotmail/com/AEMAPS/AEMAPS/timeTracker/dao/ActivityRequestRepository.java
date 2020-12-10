package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models.ActivityRequests;

import java.util.List;

@Repository
public interface ActivityRequestRepository extends JpaRepository<ActivityRequests, Long> {
    List<ActivityRequests> findByActivity_IdAndEmployee_Id(Long activityId, Long userId);
}