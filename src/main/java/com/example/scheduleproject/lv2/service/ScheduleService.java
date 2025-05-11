package com.example.scheduleproject.lv2.service;

import com.example.scheduleproject.lv2.dto.ScheduleRequestDto;
import com.example.scheduleproject.lv2.dto.ScheduleResponseDto;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface ScheduleService {

    //일정 저장
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

    List<ScheduleResponseDto> findAllSchedule();

    ScheduleResponseDto findScheduleById(Long id);


    ScheduleResponseDto updateSchedule(Long id, String title, String contents);


    void deleteSchedule(Long id);
}
