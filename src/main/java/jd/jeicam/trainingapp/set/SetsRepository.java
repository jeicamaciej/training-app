package jd.jeicam.trainingapp.set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetsRepository extends JpaRepository<Sets, Long> {
}
