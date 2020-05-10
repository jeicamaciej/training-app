package jd.jeicam.trainingapp.user;

import jd.jeicam.trainingapp.training.Training;
import jd.jeicam.trainingapp.training.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @GetMapping("/{trainingId}/{dayId}/{userId}")
    public ResponseEntity<Training> getTrainingByIdAndDayAndUser(@PathVariable Long trainingId, @PathVariable Long dayId, @PathVariable Long userId) {
        return ResponseEntity.ok(userService.getTrainingByIdAndDayAndUser(trainingId, dayId, userId));
    }

    @GetMapping("/{dayId}/{userId}")
    public ResponseEntity<List<Training>> getTrainingsByDayIdAndUserId(@PathVariable Long userId, @PathVariable Long dayId){
        return ResponseEntity.ok(userService.getTraningsByUserIdAndDayId(userId, dayId));
    }
}
