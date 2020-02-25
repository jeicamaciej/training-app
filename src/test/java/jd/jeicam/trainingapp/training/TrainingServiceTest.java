package jd.jeicam.trainingapp.training;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
public class TrainingServiceTest {
    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingService trainingService;


    @Test
    public void addTrainingShouldReturnTrainingAndInvokeSave() {
        //given
        Training training = new Training();
        when(trainingRepository.save(training)).thenReturn(training);

        //when
        Training addedTraining = trainingService.addTraining(training);

        //then
        verify(trainingRepository).save(training);
        assertEquals(training, addedTraining);
        verifyNoMoreInteractions(trainingRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullTrainingShouldThrowException() {
        //given
        when(trainingRepository.save(null)).thenThrow(IllegalArgumentException.class);

        //when
        trainingService.addTraining(null);
        verify(trainingRepository).save(null);
        verifyNoMoreInteractions(trainingRepository);
    }

    @Test
    public void getTrainingShouldReturnTraining() {
        //given
        Long id = 88L;
        Training training = new Training();
        training.setId(id);
        when(trainingRepository.findById(id)).thenReturn(Optional.of(training));

        //when
        Optional<Training> optionalTraining = trainingService.getTraining(id);

        //then
        verify(trainingRepository).findById(id);
        assertThat(optionalTraining).contains(training);
        verifyNoMoreInteractions(trainingRepository);
    }

    @Test
    public void deleteShouldReturnTrue(){
        //given
        Long id = 88L;
        when(trainingRepository.existsById(id)).thenReturn(true);

        //when
        boolean isTrainingDeleted = trainingService.deleteTraining(id);

        //then
        assertTrue(isTrainingDeleted);
        verify(trainingRepository).deleteById(id);
        verify(trainingRepository).existsById(id);
        verifyNoMoreInteractions(trainingRepository);
    }

    @Test
    public void deleteShouldReturnFalse(){
        //given
        Long id = 88L;
        when(trainingRepository.existsById(id)).thenReturn(false);

        //when
        boolean isTrainingDeleted = trainingService.deleteTraining(id);

        //then
        assertFalse(isTrainingDeleted);
        verify(trainingRepository).existsById(id);
        verify(trainingRepository, never()).deleteById(id);
        verifyNoMoreInteractions(trainingRepository);
    }

}