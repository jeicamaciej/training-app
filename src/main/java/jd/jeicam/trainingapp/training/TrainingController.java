package jd.jeicam.trainingapp.training;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/training")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TrainingController {

    private TrainingService trainingService;

    @GetMapping("/dev/all")
    @JsonView(Training.JsonViews.Get.class)
    ResponseEntity<List<Training>> getAll() {
        return ResponseEntity.ok(trainingService.getTrainings());
    }

    @GetMapping("/{id}")
    @JsonView(Training.JsonViews.GetExtended.class)
    ResponseEntity<Training> getTraining(@PathVariable Long id) {
        return trainingService.getTraining(id).map(ResponseEntity::ok).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/new")
    @JsonView(Training.JsonViews.GetExtended.class)
    ResponseEntity<Training> addNewTraining(@RequestBody Training training) {
        return ResponseEntity.ok(trainingService.addNewTraining(training));
    }

    @GetMapping("/all")
    @JsonView(Training.JsonViews.Get.class)
    public ResponseEntity<List<Training>> getAllByUsername(String username){
        return ResponseEntity.ok(trainingService.getAllByUsername(username));
    }

    @GetMapping("/{dayId}/all")
    @JsonView(Training.JsonViews.GetExtended.class)
    public ResponseEntity<List<Training>> getAllByUsernameAndDay(String username,@PathVariable Long dayId){
        return ResponseEntity.ok(trainingService.getAllByUsernameAndDay(username, dayId));
    }

}
