package jd.jeicam.trainingapp.exercise;

import jd.jeicam.trainingapp.set.Series;
import jd.jeicam.trainingapp.set.SeriesRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@AllArgsConstructor
public class ExerciseService {

    private ExerciseRepository exerciseRepository;
    private SeriesRepository seriesRepository;

    public Exercise addExercise(@NotNull Exercise newExercise) {
        return exerciseRepository.save(newExercise);
    }

    public Exercise modifyExercise(Long exerciseId, @NotNull String name) {
        if (!exerciseRepository.existsById(exerciseId)) {
            throw new IllegalArgumentException("exercise not present");
        }
        Exercise exercise = exerciseRepository.getOne(exerciseId);
        exercise.setName(name);
        return exerciseRepository.save(exercise);
    }

    public Exercise removeSeriesFromExercise(Long exerciseId, Long seriesId) {
        if (exerciseRepository.existsById(exerciseId) && seriesRepository.existsById(seriesId)) {
            Exercise exercise = exerciseRepository.getOne(exerciseId);
            Series series = seriesRepository.getOne(seriesId);

            if (exercise.getSeries().contains(series) || series.getExercise().equals(exercise)) {
                exercise.getSeries().remove(series);
                series.setExercise(null);
                seriesRepository.save(series);
                return exerciseRepository.save(exercise);
            }
            throw new IllegalArgumentException("Set not present");
        }
        throw new IllegalArgumentException("Set or exercise not present");
    }

    @SneakyThrows
    Exercise getExercise(Long id) {
        return exerciseRepository.findById(id).orElseThrow(IllegalAccessException::new);
    }

    List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }


}
