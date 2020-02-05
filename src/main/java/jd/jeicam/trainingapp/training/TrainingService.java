package jd.jeicam.trainingapp.training;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@AllArgsConstructor
public class TrainingService {
    private TrainingRepository trainingRepository;

    Training addTraining(@NotNull Training training){
        return trainingRepository.save(training);
    }

    public boolean deleteTraining(@NotNull Long id){
        if (trainingRepository.existsById(id)) {
            trainingRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

    List<Training> getTrainings(){
        return trainingRepository.findAll();
    }
}
