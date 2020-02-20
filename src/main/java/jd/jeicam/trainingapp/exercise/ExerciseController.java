package jd.jeicam.trainingapp.exercise;

import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.set.Series;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/exercise")
public class ExerciseController {

    private ExerciseService exerciseService;

    //todo:delete method

    @GetMapping
    @JsonView(Exercise.JsonViews.Get.class)
    ResponseEntity<List<Exercise>> getAllExercises(){
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }

    @PostMapping
    ResponseEntity<Exercise> addExercise(@RequestBody Exercise newExercise){
        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.addExercise(newExercise));
    }

    @PostMapping("/{id}/add")
    ResponseEntity<Series> addSeriesToExercise(@PathVariable Long id,@RequestBody Series series){
        return ResponseEntity.ok(exerciseService.addSeriesToExercise(id,series));
    }

    @GetMapping("/{id}")
    @JsonView(Exercise.JsonViews.GetExtended.class)
    ResponseEntity<Exercise> getExercise(@PathVariable Long id){
        return ResponseEntity.ok(exerciseService.getExercise(id));
    }

}
