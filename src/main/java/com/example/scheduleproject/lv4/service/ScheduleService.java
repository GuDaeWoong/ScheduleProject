package com.example.scheduleproject.lv4.service;

import com.example.scheduleproject.lv4.dto.ScheduleRequestDto;
import com.example.scheduleproject.lv4.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedule(String authorId, String updatedDate, int page,int size);

    ScheduleResponseDto findScheduleById(Long id);


    ScheduleResponseDto updateSchedule(Long id, String title, String contents, String password);

    void deleteSchedule(Long id, String password);

}
