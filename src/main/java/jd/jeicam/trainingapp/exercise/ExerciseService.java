package jd.jeicam.trainingapp.exercise;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExerciseService {
    private ExerciseRepository exerciseRepository;

    Exercise addExercise(Exercise newExercise){
        return exerciseRepository.save(newExercise);
    }

    boolean deleteExercise(Long id){
        if(exerciseRepository.existsById(id)){
            exerciseRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

    Optional<Exercise> getExercise(Long id){
        return exerciseRepository.findById(id);
    }

    List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }
}
