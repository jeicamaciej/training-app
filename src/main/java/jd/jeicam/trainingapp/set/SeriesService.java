package jd.jeicam.trainingapp.set;

import jd.jeicam.trainingapp.exercise.Exercise;
import jd.jeicam.trainingapp.exercise.ExerciseRepository;
import jd.jeicam.trainingapp.user.User;
import jd.jeicam.trainingapp.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SeriesService {

    private SeriesRepository seriesRepository;
    private ExerciseRepository exerciseRepository;
    private UserRepository userRepository;

    public Series addSeries(String username, Long exerciseId, Integer reps, Double weight) {
        Exercise exercise = exerciseRepository.getOne(exerciseId);
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);

        Series newSeries = new Series();
        newSeries.setWeight(weight);
        newSeries.setExercise(exercise);
        newSeries.setReps(reps);
        newSeries.setUser(user);
        return seriesRepository.save(newSeries);
    }

    public Series modifySeries(Long seriesId, Integer reps, Double weight, String comment) {
        if(!seriesRepository.existsById(seriesId)){
            throw new IllegalArgumentException("series not present");
        }

        Series series = seriesRepository.getOne(seriesId);
        if (reps != null) {
            series.setReps(reps);
        }
        if (weight != null) {
            series.setWeight(weight);
        }
        if (comment != null) {
            series.setComment(comment);
        }
        return seriesRepository.save(series);
    }
}
