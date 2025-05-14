package com.example.scheduleproject.lv4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Author {

    private long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Author(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }
}
