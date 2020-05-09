package jd.jeicam.trainingapp.day;

import jd.jeicam.trainingapp.training.Training;
import jd.jeicam.trainingapp.training.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;

@Service
@AllArgsConstructor
public class DayService {

    private DayRepository dayRepository;
    private TrainingService trainingService;

    public Day addDay(){
        return dayRepository.save(new Day());
    }
}
