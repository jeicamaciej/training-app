package jd.jeicam.trainingapp.training;

import com.fasterxml.jackson.annotation.JsonFormat;
import jd.jeicam.trainingapp.exercise.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date date;

    @ManyToMany
    private List<Exercise> exercises;

}
