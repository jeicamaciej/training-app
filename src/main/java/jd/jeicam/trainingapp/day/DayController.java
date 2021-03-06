package jd.jeicam.trainingapp.day;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/day")
@CrossOrigin(origins = "*")
public class DayController {

    private DayService dayService;

    @PostMapping("/new")
    @JsonView(Day.JsonViews.Get.class)
    public ResponseEntity<Day> addDay(){
        return ResponseEntity.ok(dayService.addDay());
    }

    @GetMapping("/{id}")
    @JsonView(Day.JsonViews.Get.class)
    public ResponseEntity<Day> getDay(@PathVariable Long id){
        return ResponseEntity.ok(dayService.getDay(id));
    }

    @GetMapping("/all")
    @JsonView(Day.JsonViews.Get.class)
    public ResponseEntity<List<Day>> getAll(){
        return ResponseEntity.ok(dayService.getAllDays());
    }

    @GetMapping("/get/{date}")
    @JsonView(Day.JsonViews.Get.class)
    public ResponseEntity<Day> getByDayOrCreateNew(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")Date date, String username){
        return ResponseEntity.ok(dayService.getDayByDateOrCreateNew(date, username));
    }
}
