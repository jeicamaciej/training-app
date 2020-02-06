package jd.jeicam.trainingapp.set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int reps;
    private String comment;
}
