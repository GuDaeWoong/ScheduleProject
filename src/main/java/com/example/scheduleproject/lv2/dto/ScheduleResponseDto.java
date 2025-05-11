package com.example.scheduleproject.lv2.dto;

import com.example.scheduleproject.lv2.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String creator;
    private String password;
    private String title;
    private String contents;
    private String createdDate;
    private String updatedDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.creator = schedule.getCreator();
        this.password = schedule.getPassword();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdDate = schedule.getCreatedDate();
        this.updatedDate = schedule.getUpdatedDate();
    }
}
