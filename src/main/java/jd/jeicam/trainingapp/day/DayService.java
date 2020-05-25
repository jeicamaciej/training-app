package jd.jeicam.trainingapp.day;

import jd.jeicam.trainingapp.training.Training;
import jd.jeicam.trainingapp.training.TrainingRepository;
import jd.jeicam.trainingapp.user.User;
import jd.jeicam.trainingapp.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DayService {

    private DayRepository dayRepository;
    private UserRepository userRepository;
    private TrainingRepository trainingRepository;

    private Long getIdFromUsername(String username) {
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);
        return user.getId();
    }

    public Day addDay() {
        return dayRepository.save(new Day());
    }

    public List<Day> getAllDays() {
        return dayRepository.findAll();
    }

    public Day getDay(Long dayId) {
        return dayRepository.findById(dayId).orElseThrow(IllegalArgumentException::new);
    }

    public Day getDayByDateOrCreateNew(Date date, String username) {
        Long userId = getIdFromUsername(username);
        Optional<Day> day = dayRepository.findByDateAndUserId(date, userId);
        return day.orElseGet(() -> {
            Day newDay = new Day();
            newDay.setDate(date);
            newDay.setUser(userRepository.findByUsernameOrEmail(username, username)
                    .orElseThrow(IllegalArgumentException::new));
            return dayRepository.save(newDay);
        });
    }

    public Day getDayByDateOrCreateNewv2(Date date, String username) {
        Long userId = getIdFromUsername(username);
        Optional<Day> day = dayRepository.findByDateAndUserId(date, userId);
        return day.orElseGet(() -> {
            Day newDay = new Day();
            newDay.setDate(date);
            newDay.setUser(userRepository.getOne(userId));
            dayRepository.save(newDay);

            Training training = new Training();
            training.setUser(userRepository.getOne(userId));
            training.setDesc("edit description using button below");
            training.setExercises(new ArrayList<>());
            training.setDay(newDay);
            trainingRepository.save(training);

            Day tempDay = dayRepository.getOne(newDay.getId());
            tempDay.setTraining(training);
            return dayRepository.save(tempDay);
        });
    }

}
