package jd.jeicam.trainingapp.training;

import jd.jeicam.trainingapp.exercise.Exercise;
import jd.jeicam.trainingapp.exercise.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TrainingService {

    private TrainingRepository trainingRepository;
    private ExerciseRepository exerciseRepository;

    Training addTraining(@NotNull Training training) {
        return trainingRepository.save(training);
    }

    public boolean deleteTraining(@NotNull Long id) {
        if (trainingRepository.existsById(id)) {
            trainingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    List<Training> getTrainings() {
        return trainingRepository.findAll();
    }

    Optional<Training> getTraining(@NotNull Long id) {
        return trainingRepository.findById(id);
    }

    boolean addExerciseToTraining(@NotNull Long trainingId, @NotNull Long exerciseId) {
        Optional<Training> training = trainingRepository.findById(trainingId);
        Optional<Exercise> exercise = exerciseRepository.findById(exerciseId);

        if (training.isPresent() && exercise.isPresent()) {
            training.get().getExercises().add(exercise.get());
            trainingRepository.save(training.get());
            return true;
        }
        return false;
    }
}
