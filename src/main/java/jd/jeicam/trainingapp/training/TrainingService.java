package jd.jeicam.trainingapp.training;

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
        } else {
            return false;
        }
    }

    List<Training> getTrainings() {
        return trainingRepository.findAll();
    }

    Optional<Training> getTraining(@NotNull Long id){
        return trainingRepository.findById(id);
    }

//    Boolean addExistingExercise(@NotNull Long trainingId, @NotNull Long exerciseId){
//         return trainingRepository.findById(trainingId).get().getExercises().add(exerciseRepository.findById(exerciseId).get());
//    }

}
