package jd.jeicam.trainingapp.training;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Training {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
}
