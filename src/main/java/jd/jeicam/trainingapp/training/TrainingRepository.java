package jd.jeicam.trainingapp.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    Training findByIdAndUserId(Long trainingId, Long userId);
    List<Training> findAllByUserId(Long userId);
    List<Training> findAllByUserIdAndDayId(Long userId, Long dayId);
}
