package com.polstat.ukmbulstik.service;

import com.polstat.ukmbulstik.dto.ScheduleDto;
import com.polstat.ukmbulstik.entity.Schedule;
import com.polstat.ukmbulstik.mapper.ScheduleMapper;
import com.polstat.ukmbulstik.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public ScheduleDto createSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = ScheduleMapper.toEntity(scheduleDto);
        schedule = scheduleRepository.save(schedule);
        return ScheduleMapper.toDto(schedule);
    }

    @Override
    public ScheduleDto getSchedule(Long id) {
        return scheduleRepository.findById(id)
                .map(ScheduleMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<ScheduleDto> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
