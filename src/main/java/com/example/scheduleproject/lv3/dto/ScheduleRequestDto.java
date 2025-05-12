package com.example.scheduleproject.lv3.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    private Long authorId;
    private String title;
    private String contents;
    private String password;

}
