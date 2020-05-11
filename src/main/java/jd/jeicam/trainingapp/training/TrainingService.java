package jd.jeicam.trainingapp.training;

import jd.jeicam.trainingapp.day.Day;
import jd.jeicam.trainingapp.day.DayRepository;
import jd.jeicam.trainingapp.exercise.Exercise;
import jd.jeicam.trainingapp.exercise.ExerciseRepository;
import jd.jeicam.trainingapp.user.User;
import jd.jeicam.trainingapp.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TrainingService {

    private TrainingRepository trainingRepository;
    private ExerciseRepository exerciseRepository;
    private UserRepository userRepository;

    private Long getIdFromUsername(String username) {
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);
        return user.getId();
    }

    public Training getOneByIdAndUsername(Long trainingId, String username) {
        return trainingRepository.findByIdAndUserId(trainingId, getIdFromUsername(username));
    }

    public List<Training> getAllByUsernameAndDay(String username, Long dayId) {
        return trainingRepository.findAllByUserIdAndDayId(getIdFromUsername(username), dayId);
    }

    public List<Training> getAllByUsername(String username){
        return trainingRepository.findAllByUserId(getIdFromUsername(username));
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
        exerciseRepository.save(exercise);
        return trainingRepository.save(training);
    }


}
