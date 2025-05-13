package com.example.scheduleproject.lv4.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AuthorRequestDto {

    private String name;
    private String email;
    @NotNull
    private String password;
}
