package com.example.scheduleproject.lv2.repository;

import com.example.scheduleproject.lv2.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv2.entity.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedule();

    Optional<Schedule> findScheduleById(Long id);

    Schedule findScheduleByIdOrElseThrow(Long id);

    int updateSchedule(Long id, String title, String contents);

    int deleteSchedule(Long id);
}
