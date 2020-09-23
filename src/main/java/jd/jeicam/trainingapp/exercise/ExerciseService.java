package jd.jeicam.trainingapp.exercise;

import jd.jeicam.trainingapp.set.Series;
import jd.jeicam.trainingapp.set.SeriesRepository;
import jd.jeicam.trainingapp.training.Training;
import jd.jeicam.trainingapp.training.TrainingRepository;
import jd.jeicam.trainingapp.user.User;
import jd.jeicam.trainingapp.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final SeriesRepository seriesRepository;
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    @Transactional
    public Exercise addExercise(String username, Long trainingId, String name) {
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);
        Training training = trainingRepository.getOne(trainingId);
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setTrainings(new ArrayList<>());
        exercise.getTrainings().add(training);
        exercise.setUser(user);
        exercise.setSeries(new ArrayList<>());
        training.getExercises().add(exercise);
        return exerciseRepository.save(exercise);
    }

    public Exercise modifyExercise(Long exerciseId, String name) {
        if (!exerciseRepository.existsById(exerciseId)) {
            throw new IllegalArgumentException("exercise not present");
        }
        Exercise exercise = exerciseRepository.getOne(exerciseId);
        exercise.setName(name);
        return exerciseRepository.save(exercise);
    }

    @Transactional
    public Exercise removeSeriesFromExercise(Long exerciseId, Long seriesId) {
        if (!seriesRepository.existsById(seriesId) || !exerciseRepository.existsById(exerciseId)) {
            throw new IllegalArgumentException("training or exercise not present");
        }

        Series series = seriesRepository.getOne(seriesId);
        Exercise exercise = exerciseRepository.getOne(exerciseId);

        if (!series.getExercise().equals(exercise) || !exercise.getSeries().contains(series)) {
            throw new IllegalArgumentException("exercise not present");
        }
        seriesRepository.deleteById(seriesId);
        return exerciseRepository.save(exercise);

    }

    @SneakyThrows
    Exercise getExercise(Long id) {
        return exerciseRepository.findById(id).orElseThrow(IllegalAccessException::new);
    }

    List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }


}
