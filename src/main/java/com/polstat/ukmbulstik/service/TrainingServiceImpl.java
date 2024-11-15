package com.polstat.ukmbulstik.service;

import com.polstat.ukmbulstik.dto.TrainingDto;
import com.polstat.ukmbulstik.entity.Schedule;
import com.polstat.ukmbulstik.entity.Training;
import com.polstat.ukmbulstik.mapper.TrainingMapper;
import com.polstat.ukmbulstik.repository.ScheduleRepository;
import com.polstat.ukmbulstik.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public TrainingDto registerTraining(TrainingDto trainingDto) {
        Schedule schedule = scheduleRepository.findById(trainingDto.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));

        Training training = TrainingMapper.toEntity(trainingDto, schedule);
        training = trainingRepository.save(training);
        return TrainingMapper.toDto(training);
    }

    @Override
    public boolean cancelTraining(Long trainingId) {
        return trainingRepository.findById(trainingId).map(training -> {
            training.setCancelled(true);
            trainingRepository.save(training);
            return true;
        }).orElse(false);
    }

    @Override
    public List<TrainingDto> getTrainingsBySchedule(Long scheduleId) {
        return trainingRepository.findByScheduleId(scheduleId).stream()
                .map(TrainingMapper::toDto)
                .collect(Collectors.toList());
    }
}
