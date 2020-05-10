package jd.jeicam.trainingapp.user;

import jd.jeicam.trainingapp.day.DayRepository;
import jd.jeicam.trainingapp.exercise.ExerciseRepository;
import jd.jeicam.trainingapp.training.Training;
import jd.jeicam.trainingapp.training.TrainingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private TrainingRepository trainingRepository;
    private DayRepository dayRepository;
    private ExerciseRepository exerciseRepository;

    public Training getTrainingByIdAndDayAndUser(Long traningId, Long dayId, Long userId){
        return trainingRepository.findByIdAndDayIdAndUserId(traningId, dayId, userId);
    }

    public List<Training> getTraningsByUserIdAndDayId(Long userId, Long dayId){
        return trainingRepository.findAllByUserIdAndDayId(userId, dayId);
    }

    public List<Training> getAllByUserId(Long userId){
        return trainingRepository.findAllByUserId(userId);
    }


}
