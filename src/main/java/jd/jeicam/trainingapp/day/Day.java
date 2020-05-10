package jd.jeicam.trainingapp.day;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.calories_calculator.Meal;
import jd.jeicam.trainingapp.security.user.User;
import jd.jeicam.trainingapp.set.Series;
import jd.jeicam.trainingapp.training.Training;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TRAININGAPP_DAY")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Day {

    public interface JsonViews {
        interface Get extends Training.JsonViews.Get {
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Series.JsonViews.Get.class)
    private long id;

    @OneToMany(mappedBy = "day")
    @JsonView(Series.JsonViews.Get.class)
    private List<Training> trainings;

//    @OneToMany(mappedBy = "day")
//    private List<Meal> meals;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NaturalId
    @JsonView(Series.JsonViews.Get.class)
    private Date date;

    @ManyToOne
    @JsonView(JsonViews.Get.class)
    private User user;

    public Day() {
        this.trainings = new ArrayList<>();
//        this.meals = new ArrayList<>();
        this.date = Date.from(Instant.now());
    }
}
