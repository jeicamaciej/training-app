package jd.jeicam.trainingapp.user;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @GetMapping("/all")
    @JsonView(User.JsonViews.GetAdminView.class)
    public ResponseEntity<List<User>> getAllUsers(String username) throws IllegalAccessException {
        try {
            return ResponseEntity.ok(userService.getAllUsers(username));
        }
        catch(IllegalAccessException e){
            throw new AuthorizationServiceException("unauthorized");
        }
        }
}
