package jd.jeicam.trainingapp.day;

import jd.jeicam.trainingapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
    Optional<Day> findByDateAndUserId(@Temporal(TemporalType.DATE)Date date, Long userId);
    Boolean existsByDate(Date date);
}
