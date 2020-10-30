package jd.jeicam.trainingapp.user;

import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.calories_calculator.Meal;
import jd.jeicam.trainingapp.day.Day;
import jd.jeicam.trainingapp.exercise.Exercise;
import jd.jeicam.trainingapp.security.role.Role;
import jd.jeicam.trainingapp.set.Series;
import jd.jeicam.trainingapp.training.Training;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
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
@AllArgsConstructor
public class User {

    public interface JsonViews {
        interface Get extends Role.JsonViews.Get {
        }

        interface GetExtended extends Get {
        }

        interface GetAdminView {
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({JsonViews.Get.class, JsonViews.GetAdminView.class})
    private long id;

    @NotBlank
    //@Size(max = 20)
    private String name;

    @NotBlank
    //@Size(max = 20)
    @JsonView({JsonViews.Get.class, JsonViews.GetAdminView.class})
    private String username;

    @NotBlank
    //@Size(max = 40)
    @NaturalId
    @JsonView({JsonViews.Get.class, JsonViews.GetAdminView.class})
    private String email;

    @NotBlank
    //@Size(max = 60)
    private String password;

    @JsonView(JsonViews.Get.class)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TRAININGAPP_USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Day> days;
    @OneToMany(mappedBy = "user")
    private List<Training> trainings;
    @OneToMany(mappedBy = "user")
    private List<Exercise> exercises;
    @OneToMany(mappedBy = "user")
    private List<Series> series;
    @OneToMany(mappedBy = "user")
    private List<Meal> meals;
    @ElementCollection
    private List<String> products;

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }


}
