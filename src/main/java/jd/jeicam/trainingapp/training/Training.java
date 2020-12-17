package jd.jeicam.trainingapp.training;

import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.day.Day;
import jd.jeicam.trainingapp.exercise.Exercise;
import jd.jeicam.trainingapp.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "TRAININGAPP_TRAINING")
@NoArgsConstructor
public class Training {

    public interface JsonViews {

        interface Get extends Exercise.JsonViews.Get {
        }

        interface GetExtended extends Get, Exercise.JsonViews.Get, Day.JsonViews.Get, User.JsonViews.Get {

        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(JsonViews.Get.class)
    private Long id;

    @JsonView(JsonViews.Get.class)
    @Column(name = "DESCRIPTION")
    private String desc;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TRAININGAPP_TRAINING_EXERCISE",
            joinColumns = @JoinColumn(name = "TRAINING_ID"),
            inverseJoinColumns = @JoinColumn(name = "EXERCISE_ID")
    )
    @JsonView(JsonViews.Get.class)
    private List<Exercise> exercises;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DAY_ID", referencedColumnName = "id")
    @JsonView(JsonViews.GetExtended.class)
    private Day day;

    @ManyToOne
    @JsonView(JsonViews.GetExtended.class)
    private User user;
}

