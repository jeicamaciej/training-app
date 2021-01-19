package jd.jeicam.trainingapp.calories_calculator;

import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.day.Day;
import jd.jeicam.trainingapp.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRAININGAPP_MEAL")
public class Meal {

    public interface JsonViews{
        interface Get {

        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(JsonViews.Get.class)
    private long id;

    @JsonView(JsonViews.Get.class)
    private int proteins;

    @JsonView(JsonViews.Get.class)
    private int carbs;

    @JsonView(JsonViews.Get.class)
    private int fats;

    @JsonView(JsonViews.Get.class)
    private String mealName;

    @ManyToOne
    private Day day;

    @ManyToOne
    @JsonView(Day.JsonViews.Get.class)
    private User user;

    @ElementCollection
    @JsonView(JsonViews.Get.class)
    private List<String> products;
}
