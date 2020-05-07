package jd.jeicam.trainingapp.calories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "MEAL")
public class Meal {
    @Id
    private long id;

    private int proteins;

    private int carbs;

    private int fats;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private MealType mealType;
}
