package jd.jeicam.trainingapp.set;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SeriesService {

    private SeriesRepository seriesRepository;

    public Series addSeries(Series newSeries) {
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
