package jd.jeicam.trainingapp.set;

import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.exercise.Exercise;
import jd.jeicam.trainingapp.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Data
@ToString
@Table(name = "TRAININGAPP_SERIES")
@Getter
@Setter
@EqualsAndHashCode
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

    @ManyToOne
    @JsonView(JsonViews.GetExtended.class)
    private User user;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Series)) return false;
//        Series series = (Series) o;
//        return id == series.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
