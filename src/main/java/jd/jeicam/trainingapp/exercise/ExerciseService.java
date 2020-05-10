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

    public Exercise addExercise(Exercise newExercise) {
        return exerciseRepository.save(newExercise);
    }

    public Exercise modifyExercise(Long exerciseId, String name){
        if(!exerciseRepository.existsById(exerciseId)){
            throw new IllegalArgumentException("exercise not present");
        }
        if (name != null) {
            Exercise exercise = exerciseRepository.getOne(exerciseId);
            exercise.setName(name);
            return exerciseRepository.save(exercise);
        }
        throw new IllegalArgumentException("name must not be null");
    }

    public Exercise removeSeriesFromExercise(Long exerciseId, Long seriesId) {
        if (exerciseRepository.existsById(exerciseId) && seriesRepository.existsById(seriesId)) {
            Exercise exercise = exerciseRepository.getOne(exerciseId);
            Series series = seriesRepository.getOne(seriesId);

            if (exercise.getSeries().contains(series)) {
                exercise.getSeries().remove(series);
                series.setExercise(null);
                seriesRepository.save(series);
                return exerciseRepository.save(exercise);
            }
            throw new IllegalArgumentException("Set not present");
        }
        throw new IllegalArgumentException("Set or exercise not present");
    }

    public Exercise addSeriesToExercise(Long seriesId, Long exerciseId) {
        Series series = seriesRepository.getOne(seriesId);
        Exercise exercise = exerciseRepository.getOne(exerciseId);

        exercise.getSeries().add(series);
        series.setExercise(exercise);

        seriesRepository.save(series);
        return exerciseRepository.save(exercise);
    }

    Exercise getExercise(Long id) {
        return exerciseRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }


}
