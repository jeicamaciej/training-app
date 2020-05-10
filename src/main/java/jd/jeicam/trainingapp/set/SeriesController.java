package jd.jeicam.trainingapp.set;

import jd.jeicam.trainingapp.exercise.Exercise;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/series")
public class SeriesController {
    private SeriesService seriesService;

    @PostMapping("/new")
    public ResponseEntity<Series> addSeries(@RequestParam(required = true, name = "reps") int reps, @RequestParam(required = false, name = "weight") double weight){
        return ResponseEntity.ok(seriesService.addSeries(reps, weight));
    }

    @PostMapping("/edit/{seriesId}")
    public ResponseEntity<Series> modifyExercise(@PathVariable Long seriesId, @RequestParam(required = false) Integer reps, @RequestParam(required = false) Double weight, @RequestParam(required = false) String comment){
        return ResponseEntity.ok(seriesService.modifySeries(seriesId, reps, weight, comment));
    }
}
