package jd.jeicam.trainingapp.security.user;

import jd.jeicam.trainingapp.security.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "TRAININGAPP_USER", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    //@Size(max = 20)
    private String name;

    @NotBlank
    //@Size(max = 20)
    private String username;

    @NotBlank
    //@Size(max = 40)
    @NaturalId
    private String email;

    @NotBlank
    //@Size(max = 60)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name =  "TRAININGAPP_USER_ROLES",
        joinColumns = @JoinColumn(name = "USER_ID"),
        inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
