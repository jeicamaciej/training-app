package jd.jeicam.trainingapp.exercise;

import jd.jeicam.trainingapp.set.Series;
import jd.jeicam.trainingapp.set.SeriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExerciseService {

    private ExerciseRepository exerciseRepository;
    private SeriesRepository seriesRepository;

    Exercise addExercise(Exercise newExercise) {
        return exerciseRepository.save(newExercise);
    }

    boolean deleteExercise(Long id) {
        if (exerciseRepository.existsById(id)) {
            exerciseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    Exercise getExercise(Long id) {
        return exerciseRepository.getOne(id);
    }

    List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    Series addSeriesToExercise(Long exerciseId, Series series) {
        exerciseRepository.getOne(exerciseId).getSeries().add(series);
        return seriesRepository.save(series);
    }


}
