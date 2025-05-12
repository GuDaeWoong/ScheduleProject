package com.example.scheduleproject.lv3.dto;

import com.example.scheduleproject.lv3.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private Long authorId;
    private String title;
    private String contents;
    private String createdDate;
    private String updatedDate;

}
