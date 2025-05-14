package com.example.scheduleproject.lv4.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {


    private Long id;
    private Long authorId;
    private String title;
    private String contents;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Schedule(Long authorId, String title, String contents) {
        this.authorId = authorId;
        this.title = title;
        this.contents = contents;
    }


}
