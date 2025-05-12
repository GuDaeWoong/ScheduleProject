package com.example.scheduleproject.lv1.service;

import com.example.scheduleproject.lv1.dto.ScheduleRequestDto;
import com.example.scheduleproject.lv1.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    //일정 저장
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedule();

    ScheduleResponseDto findScheduleById(String creator);

}
