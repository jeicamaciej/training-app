package jd.jeicam.trainingapp.training;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRAININGAPP_TRAINING")
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonView(JsonViews.Get.class)
    @Column(name = "TRAINING_DATE")
    private Date date;

    @JsonView(JsonViews.Get.class)
    @Column(name = "DESCRIPTION")
    private String desc;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name =  "TRAININGAPP_TRAINING_EXERCISE",
            joinColumns = @JoinColumn(name = "TRAINING_ID"),
            inverseJoinColumns = @JoinColumn(name = "EXERCISE_ID")
    )
    private Set<Exercise> exercises;


}

