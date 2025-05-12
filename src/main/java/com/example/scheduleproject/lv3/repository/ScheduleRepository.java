package com.example.scheduleproject.lv3.repository;

import com.example.scheduleproject.lv3.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv3.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllByAuthorSchedule(Long authorId);

}
