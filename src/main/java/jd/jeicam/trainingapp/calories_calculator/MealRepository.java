package jd.jeicam.trainingapp.calories_calculator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findAllByDayIdAndUserId(Long dayId, Long userId);
}
