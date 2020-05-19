package jd.jeicam.trainingapp.day;

import jd.jeicam.trainingapp.user.User;
import jd.jeicam.trainingapp.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DayService {

    private DayRepository dayRepository;
    private UserRepository userRepository;

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
            newDay.setUser(userRepository.getOne(userId));
            return dayRepository.save(newDay);
        });
    }

}
