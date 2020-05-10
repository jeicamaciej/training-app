package jd.jeicam.trainingapp.day;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DayService {

    private DayRepository dayRepository;

    public Day addDay(){
        return dayRepository.save(new Day());
    }

    public List<Day> getAllDays(){
        return dayRepository.findAll();
    }

    public Day getDay(Long dayId){
        return dayRepository.findById(dayId).orElseThrow(IllegalArgumentException::new);
    }
}
