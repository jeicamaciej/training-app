package jd.jeicam.trainingapp.day;

import jd.jeicam.trainingapp.training.Training;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/day")
public class DayController {

    private DayService dayService;

    @PostMapping(path = "/new")
    public ResponseEntity<Day> addDay(){
        return ResponseEntity.ok(dayService.addDay());
    }
}
