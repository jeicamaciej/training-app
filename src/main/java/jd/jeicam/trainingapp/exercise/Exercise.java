package jd.jeicam.trainingapp.exercise;

import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.set.Series;
import jd.jeicam.trainingapp.training.Training;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "EXERCISE")
public class Exercise {

    public interface JsonViews {
        interface Get extends Series.JsonViews.Get {

        }

        interface GetExtended extends Get, Training.JsonViews.Get, Series.JsonViews.Get {

        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @JsonView(JsonViews.Get.class)
    private long id;

    @Column
    @JsonView(JsonViews.Get.class)
    private String name;

    @ManyToMany(mappedBy = "exercises")
    @JsonView(JsonViews.GetExtended.class)
    private List<Training> trainings;


    @OneToMany(mappedBy = "exercise")
    @JsonView(JsonViews.Get.class)
    private List<Series> series;

}
