package com.example.scheduleproject.lv4.repository;

import com.example.scheduleproject.lv4.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv4.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);


    List<ScheduleResponseDto> findAllSchedule(String authorId, String updatedDate, int page, int size);

    Schedule findMemoByIdOrElseThrow(Long id);

    int updateSchedule(Long id, String title, String contents);

    int deleteSchedule(Long id);

}
