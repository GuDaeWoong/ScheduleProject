package com.example.scheduleproject.lv3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private Long authorId;
    private String title;
    private String contents;
    private String createdDate;
    private String updatedDate;

    public Schedule(Long authorId, String title, String contents) {
        this.authorId = authorId;
        this.title = title;
        this.contents = contents;
    }


}
