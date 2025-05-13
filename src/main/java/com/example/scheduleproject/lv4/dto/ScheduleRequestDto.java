package com.example.scheduleproject.lv4.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    @NotNull
    private Long authorId;
    @NotNull
    private String password;
    private String title;
    private String contents;

}
