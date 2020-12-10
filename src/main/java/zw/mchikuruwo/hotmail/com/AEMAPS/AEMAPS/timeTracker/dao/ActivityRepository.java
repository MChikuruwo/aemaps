package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.models.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
