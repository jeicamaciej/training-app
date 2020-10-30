package jd.jeicam.trainingapp.calories_calculator;

import com.fasterxml.jackson.annotation.JsonView;
import jd.jeicam.trainingapp.day.Day;
import jd.jeicam.trainingapp.user.User;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude="user")
@ToString(exclude = "user")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRAININGAPP_MEAL")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int proteins;

    private int carbs;

    private int fats;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private MealType mealType;

    @ManyToOne
    private Day day;

    @ManyToOne
    @JsonView(Day.JsonViews.Get.class)
    private User user;

    @ElementCollection
    private List<String> products;
}
