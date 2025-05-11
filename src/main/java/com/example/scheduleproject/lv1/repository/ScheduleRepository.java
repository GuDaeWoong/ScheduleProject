package com.example.scheduleproject.lv1.repository;

import com.example.scheduleproject.lv1.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv1.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedule();

    Optional<Schedule> findScheduleById(Long id);

    Schedule findScheduleByIdOrElseThrow(Long id);
}
