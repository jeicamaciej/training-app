package jd.jeicam.trainingapp.training;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/training")
@AllArgsConstructor
public class TrainingController {

    private TrainingService trainingService;

    @PostMapping
    ResponseEntity<Training> addTraining(@RequestBody Training training){
        return ResponseEntity.status(HttpStatus.CREATED).body(trainingService.addTraining(training));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteBook(@PathVariable Long id){
        if (trainingService.deleteTraining(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping()
    ResponseEntity<List<Training>> getTrainings(){
        return ResponseEntity.ok(trainingService.getTrainings());
    }
}
