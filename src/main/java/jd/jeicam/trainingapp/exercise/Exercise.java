package jd.jeicam.trainingapp.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Exercise {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private String name;

    //todo:refactor to Set
    //todo: add tempo

    @ElementCollection
    private List<Integer> sets;

}
