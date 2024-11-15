package com.polstat.ukmbulstik.mapper;

import com.polstat.ukmbulstik.dto.ScheduleDto;
import com.polstat.ukmbulstik.entity.Schedule;

public class ScheduleMapper {

    public static Schedule toEntity(ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule();
        schedule.setTitle(scheduleDto.getTitle());
        schedule.setDateTime(scheduleDto.getDateTime());
        schedule.setLocation(scheduleDto.getLocation());
        return schedule;
    }

    public static ScheduleDto toDto(Schedule schedule) {
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setTitle(schedule.getTitle());
        scheduleDto.setDateTime(schedule.getDateTime());
        scheduleDto.setLocation(schedule.getLocation());
        return scheduleDto;
    }
}
