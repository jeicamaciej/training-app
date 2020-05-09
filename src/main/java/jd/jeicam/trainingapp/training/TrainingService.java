package jd.jeicam.trainingapp.training;

import jd.jeicam.trainingapp.day.Day;
import jd.jeicam.trainingapp.day.DayRepository;
import jd.jeicam.trainingapp.exercise.ExerciseRepository;
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
    private DayRepository dayRepository;

    public Training addNewTraining(Long dayId){
        Day day = dayRepository.findById(dayId).orElseThrow(IllegalArgumentException::new);

        Training training = new Training();
        training.setExercises(new ArrayList<>());
        training.setDay(day);

        day.getTrainings().add(training);
        return trainingRepository.save(training);
    }

//    public boolean deleteTraining(@NotNull Long id) {
//        if (trainingRepository.existsById(id)) {
//            trainingRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }

    List<Training> getTrainings() {
        return trainingRepository.findAll();
    }

    Optional<Training> getTraining(@NotNull Long id) {
        return trainingRepository.findById(id);
    }

}
