package com.polstat.ukmbulstik.service;

import com.polstat.ukmbulstik.dto.TrainingDto;
import java.util.List;

public interface TrainingService {
    TrainingDto registerTraining(TrainingDto trainingDto);
    boolean cancelTraining(Long trainingId);
    List<TrainingDto> getTrainingsBySchedule(Long scheduleId);
}
