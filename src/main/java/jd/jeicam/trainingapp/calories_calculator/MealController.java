package jd.jeicam.trainingapp.calories_calculator;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/api/meal")
public class MealController {

    private MealService mealService;

    @GetMapping("new/{date}")
    public ResponseEntity<Meal> addMeal(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")Date date, String username) {
        return ResponseEntity.ok(mealService.addEmptyMeal(date, username));
    }
}
