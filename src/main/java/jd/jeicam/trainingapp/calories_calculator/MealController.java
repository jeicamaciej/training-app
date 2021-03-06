package jd.jeicam.trainingapp.calories_calculator;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/meal")
public class MealController {

    private final MealService mealService;

    @PostMapping("new/{date}")
    @JsonView(Meal.JsonViews.Get.class)
    public ResponseEntity<Meal> addMeal(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, String username) {
        return ResponseEntity.ok(mealService.addEmptyMeal(date, username));
    }

    @GetMapping("/all/{date}")
    @JsonView(Meal.JsonViews.Get.class)
    public ResponseEntity<List<Meal>> findAllByDateAndUsername(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, String username) {
        return ResponseEntity.ok(mealService.getAllMealsByDate(date, username));
    }
}
