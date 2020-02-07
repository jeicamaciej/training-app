package jd.jeicam.trainingapp.exercise;

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
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "exercise")
    private List<Training> trainings;

}
