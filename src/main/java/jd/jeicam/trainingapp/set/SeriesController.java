package jd.jeicam.trainingapp.set;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/series")
public class SeriesController {
    private SeriesService seriesService;

    @PostMapping("/{exerciseId}/add")
    @JsonView(Series.JsonViews.Get.class)
    public ResponseEntity<Series> addSeries(String username, @PathVariable Long exerciseId, @RequestParam (name = "reps") Integer reps, @RequestParam (name = "weight")Double weight){
        return ResponseEntity.ok(seriesService.addSeries(username, exerciseId, reps, weight));
    }
}
