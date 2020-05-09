package jd.jeicam.trainingapp.day;

import com.fasterxml.jackson.annotation.JsonFormat;
import jd.jeicam.trainingapp.calories_calculator.Meal;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "day")
    private List<Training> trainings;

    @OneToMany(mappedBy = "day")
    private List<Meal> meals;

    @JsonFormat(pattern="yyyy-MM-dd")
    @NaturalId
    private Date date;

    public Day() {
        this.trainings = new ArrayList<>();
        this.meals = new ArrayList<>();
        this.date = Date.from(Instant.now());
    }
}
