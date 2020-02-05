package jd.jeicam.trainingapp.exercise;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }
}
