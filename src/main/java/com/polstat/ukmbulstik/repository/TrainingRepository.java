package com.polstat.ukmbulstik.repository;

import com.polstat.ukmbulstik.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByScheduleId(Long scheduleId);
}
