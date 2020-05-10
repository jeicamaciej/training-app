package jd.jeicam.trainingapp.set;

import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "TRAININGAPP_SERIES")
public class Series {

    public interface JsonViews {
        interface Get {
        }

        interface GetExtended extends Get {
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(JsonViews.Get.class)
    @Column
    private long id;

    @JsonView(JsonViews.Get.class)
    @Column
    private int reps;

    @JsonView(JsonViews.GetExtended.class)
    @Column
    private String comment;

    @ManyToOne
    @JsonView(JsonViews.GetExtended.class)
    private Exercise exercise;

    @JsonView(JsonViews.Get.class)
    private double weight;
}
