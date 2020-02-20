package jd.jeicam.trainingapp.training;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRAINING")
public class Training {

    public interface JsonViews {

        interface Get {
        }

        interface GetExtended extends Get, Exercise.JsonViews.Get {

        }
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @JsonView(JsonViews.Get.class)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    @JsonView(JsonViews.Get.class)
    private Date date;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "TRAINING_EXERCISE",
            joinColumns = @JoinColumn(name = "TRAINING_ID"),
            inverseJoinColumns = @JoinColumn(name = "EXERCISE_ID"))
    @JsonView(JsonViews.GetExtended.class)
    private List<Exercise> exercises;
}
