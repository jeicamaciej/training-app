package jd.jeicam.trainingapp.training;

import jd.jeicam.trainingapp.day.Day;
import jd.jeicam.trainingapp.day.DayRepository;
import jd.jeicam.trainingapp.exercise.Exercise;
import jd.jeicam.trainingapp.exercise.ExerciseRepository;
import jd.jeicam.trainingapp.user.User;
import jd.jeicam.trainingapp.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final DayRepository dayRepository;

    private Long getIdFromUsername(String username) {
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);
        return user.getId();
    }

    public Training modifyTraining(Long id, String description){
        Training training = trainingRepository.getOne(id);
        training.setDesc(description);
        return trainingRepository.save(training);
    }

    public Training getTrainingOrCreateNew(Date date, String username){
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);
        Day day = dayRepository.findByDateAndUserId(date, user.getId()).orElseThrow(IllegalArgumentException::new);

        Optional<Training> training = trainingRepository.findByDayIdAndUserId(day.getId(), user.getId());
        return training.orElseGet(() -> {
            Training newTraining = new Training();
            newTraining.setDesc("description");
            newTraining.setExercises(new ArrayList<>());
            newTraining.setUser(user);
            newTraining.setDay(day);
            return trainingRepository.save(newTraining);
        });
    }

    public Training addEmptyTraining(Date date, String username){
        Long userId = getIdFromUsername(username);
        Day day = dayRepository.findByDateAndUserId(date, userId).orElseThrow(IllegalArgumentException::new);
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);
        Training training = new Training();
        training.setDay(day);
        training.setUser(user);
        training.setExercises(new ArrayList<>());
        training.setDesc("description");
        return trainingRepository.save(training);
    }

    public Training addNewTraining(Training training) {
        return trainingRepository.save(training);
    }

    List<Training> getTrainings() {
        return trainingRepository.findAll();
    }

    Optional<Training> getTraining(@NotNull Long id) {
        return trainingRepository.findById(id);
    }

    @Transactional
    public Training removeExerciseFromTraining(Long trainingId, Long exerciseId) {
        if (!trainingRepository.existsById(trainingId) || !exerciseRepository.existsById(exerciseId)) {
            throw new IllegalArgumentException("training or exercise not present");
        }

        Training training = trainingRepository.getOne(trainingId);
        Exercise exercise = exerciseRepository.getOne(exerciseId);

        if (!training.getExercises().contains(exercise) || !exercise.getTrainings().contains(training)) {
            throw new IllegalArgumentException("exercise not present");
        }

        training.getExercises().remove(exercise);
        exercise.getTrainings().remove(training);
        return training;
    }


}
