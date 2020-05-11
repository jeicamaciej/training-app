package jd.jeicam.trainingapp.user;

import jd.jeicam.trainingapp.training.TrainingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private TrainingRepository trainingRepository;
}
