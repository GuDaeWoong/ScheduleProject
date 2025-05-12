package com.example.scheduleproject.lv3.service;

import com.example.scheduleproject.lv3.dto.ScheduleRequestDto;
import com.example.scheduleproject.lv3.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllByAuthorSchedule(Long authorId);
}
