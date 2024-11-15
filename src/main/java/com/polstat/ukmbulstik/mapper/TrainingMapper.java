package com.polstat.ukmbulstik.mapper;

import com.polstat.ukmbulstik.dto.TrainingDto;
import com.polstat.ukmbulstik.entity.Schedule;
import com.polstat.ukmbulstik.entity.Training;

public class TrainingMapper {

    public static Training toEntity(TrainingDto trainingDto, Schedule schedule) {
        Training training = new Training();
        training.setId(trainingDto.getId());
        training.setSchedule(schedule);
        training.setMember(trainingDto.getMember());
        training.setNotes(trainingDto.getNotes());
        training.setCancelled(trainingDto.isCancelled());
        return training;
    }

    public static TrainingDto toDto(Training training) {
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setId(training.getId());
        trainingDto.setScheduleId(training.getSchedule().getId());
        trainingDto.setMember(training.getMember());
        trainingDto.setNotes(training.getNotes());
        trainingDto.setCancelled(training.isCancelled());
        return trainingDto;
    }
}
