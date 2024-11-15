package com.polstat.ukmbulstik.service;

import com.polstat.ukmbulstik.dto.ScheduleDto;
import java.util.List;

public interface ScheduleService {
    ScheduleDto createSchedule(ScheduleDto scheduleDto);
    ScheduleDto getSchedule(Long id);
    List<ScheduleDto> getAllSchedules();
    boolean deleteSchedule(Long id);
}
