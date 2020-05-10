package jd.jeicam.trainingapp.exercise;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    private ExerciseService exerciseService;

    @GetMapping
    @JsonView(Exercise.JsonViews.Get.class)
    ResponseEntity<List<Exercise>> getAllExercises() {
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }

    @PostMapping("/new")
    ResponseEntity<Exercise> addExercise(@RequestBody Exercise newExercise) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.addExercise(newExercise));
    }

    @GetMapping("/{id}")
    @JsonView(Exercise.JsonViews.GetExtended.class)
    ResponseEntity<Exercise> getExercise(@PathVariable Long id) {
        return ResponseEntity.ok(exerciseService.getExercise(id));
    }

    @PostMapping("/{exerciseId}/addSet/{seriesId}")
    @JsonView(Exercise.JsonViews.GetExtended.class)
    ResponseEntity<Exercise> addSeriesToExercise(@PathVariable Long seriesId, @PathVariable Long exerciseId) {
        return ResponseEntity.ok(exerciseService.addSeriesToExercise(seriesId, exerciseId));
    }

    @PostMapping("/{exerciseId}/removeSet/{seriesId}")
    @JsonView(Exercise.JsonViews.GetExtended.class)
    ResponseEntity<Exercise> removeSeriesFromExercise(@PathVariable Long exerciseId, @PathVariable Long seriesId) {
        return ResponseEntity.ok(exerciseService.removeSeriesFromExercise(exerciseId, seriesId));
    }

    @PostMapping("/modify/{exerciseId}")
    @JsonView(Exercise.JsonViews.GetExtended.class)
    ResponseEntity<Exercise> modifyExercise(@PathVariable Long exerciseId, @RequestParam String name) {
        return ResponseEntity.ok(exerciseService.modifyExercise(exerciseId, name));
    }

}
