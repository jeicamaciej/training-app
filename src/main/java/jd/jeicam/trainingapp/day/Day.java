package jd.jeicam.trainingapp.day;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.user.User;
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
        interface Get {
        }
        interface GetExtended extends Get{

        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(JsonViews.Get.class)
    private long id;

    //@OneToMany(mappedBy = "day")
    @OneToOne(mappedBy = "day")
    @JsonView(JsonViews.Get.class)
    private Training training;

//    @OneToMany(mappedBy = "day")
//    private List<Meal> meals;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonView(JsonViews.Get.class)
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JsonView(JsonViews.GetExtended.class)
    private User user;

    public Day() {
        //this.trainings = new ArrayList<>();
//        this.meals = new ArrayList<>();
        //this.training = new Training();
        this.date = Date.from(Instant.now());
    }
}
