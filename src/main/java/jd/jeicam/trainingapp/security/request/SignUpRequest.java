package jd.jeicam.trainingapp.security.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    //@Size(min = 2, max = 40)
    private String name;

    @NotBlank
    //@Size(min = 3, max = 40)
    private String username;

    @NotBlank
    //@Size(max = 40)
    private String email;

    @NotBlank
    //@Size(min = 8, max = 20)
    private String password;
}
