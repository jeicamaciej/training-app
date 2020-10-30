package jd.jeicam.trainingapp.user;

import jd.jeicam.trainingapp.security.role.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers(String username) throws IllegalAccessException {

        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(IllegalArgumentException::new);
        if (user.getRoles().stream().map(Role::getId).collect(Collectors.toSet()).contains(2L)) {
            return userRepository.findAll();
        }
        throw new IllegalAccessException("no permissions");
    }
}
