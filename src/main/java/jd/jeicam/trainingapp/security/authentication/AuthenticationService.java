package jd.jeicam.trainingapp.security.authentication;

import jd.jeicam.trainingapp.security.jwt.JwtAuthenticationResponse;
import jd.jeicam.trainingapp.security.jwt.JwtTokenProvider;
import jd.jeicam.trainingapp.security.request.LoginRequest;
import jd.jeicam.trainingapp.security.role.RoleRepository;
import jd.jeicam.trainingapp.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    JwtTokenProvider tokenProvider;

    public JwtAuthenticationResponse getToken(LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
        //return new JwtAuthenticationResponse(tokenProvider.generateToken(authentication));
    }

}
