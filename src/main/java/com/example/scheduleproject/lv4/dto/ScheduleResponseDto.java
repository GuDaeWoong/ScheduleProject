package com.example.scheduleproject.lv4.dto;

import com.example.scheduleproject.lv4.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private Long authorId;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDate updatedDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.authorId =schedule.getAuthorId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdDate = schedule.getCreatedDate();
        this.updatedDate = schedule.getUpdatedDate();
    }
}
