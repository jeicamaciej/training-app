package jd.jeicam.trainingapp.training;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/training")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TrainingController {

    private TrainingService trainingService;

    @PostMapping
    ResponseEntity<Training> addTraining(@RequestBody Training training) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trainingService.addTraining(training));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteBook(@PathVariable Long id) {
        if (trainingService.deleteTraining(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/all")
    @JsonView(Training.JsonViews.Get.class)
    ResponseEntity<List<Training>> getTrainings() {
        return ResponseEntity.ok(trainingService.getTrainings());
    }

    @GetMapping("/{id}")
    @JsonView(Training.JsonViews.GetExtended.class)
    ResponseEntity<Training> getTraining(@PathVariable Long id) {
        return trainingService.getTraining(id).map(ResponseEntity::ok).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{trainingId}/add/{exerciseId}")
    ResponseEntity<Boolean> addExerciseToTraining(@PathVariable Long trainingId, @PathVariable Long exerciseId) {
        if (trainingService.addExerciseToTraining(trainingId, exerciseId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
