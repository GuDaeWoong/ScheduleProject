package com.example.scheduleproject.lv4.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    @NotNull
    private Long authorId;
    @NotNull(message = "값이 비어있습니다 값을 넣어주세요")
    private String password;
    private String title;

    @Size(max = 200, message = "200자를 초과합니다 검증해주세요")
    private String contents;

}
