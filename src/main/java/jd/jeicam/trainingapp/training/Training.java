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

        interface Get {
        }

        interface GetExtended extends Get, Exercise.JsonViews.Get {

        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.Get.class)
    private Long id;

    @JsonView(JsonViews.Get.class)
    @Column(name = "DESCRIPTION")
    private String desc;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name =  "TRAININGAPP_TRAINING_EXERCISE",
            joinColumns = @JoinColumn(name = "TRAINING_ID"),
            inverseJoinColumns = @JoinColumn(name = "EXERCISE_ID")
    )
    @JsonView(JsonViews.GetExtended.class)
    private List<Exercise> exercises;

    @ManyToOne
    @JsonView(JsonViews.GetExtended.class)
    private Day day;

    @ManyToOne
    @JsonView(JsonViews.GetExtended.class)
    private User user;
}

