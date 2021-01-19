package jd.jeicam.trainingapp.calories_calculator;

import jd.jeicam.trainingapp.day.Day;
import jd.jeicam.trainingapp.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findAllByDayIdAndUserId(Long dayId, Long userId);
    Optional<Meal> findByDayAndUser(Day day, User user);
}
