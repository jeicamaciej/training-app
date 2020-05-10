package jd.jeicam.trainingapp.set;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SeriesService {

    private SeriesRepository seriesRepository;

    public Series addSeries(int reps, double weight) {
        Series series = new Series();
        series.setReps(reps);
        series.setWeight(weight);
        return seriesRepository.save(series);
    }

    public Series modifySeries(Long seriesId, Integer reps, Double weight, String comment) {
        Series series = seriesRepository.getOne(seriesId);
        if (reps != null) {
            series.setReps(reps);
        }
        if (weight != null) {
            series.setWeight(weight);
        }
        if (comment != null){
            series.setComment(comment);
        }
        return seriesRepository.save(series);
    }
}
