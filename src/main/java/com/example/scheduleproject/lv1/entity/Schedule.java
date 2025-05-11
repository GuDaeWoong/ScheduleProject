package com.example.scheduleproject.lv1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String creator;
    private String password;
    private String title;
    private String contents;
    private String updatedDate;


    public Schedule(String creator,String password,String title, String contents) {
        this.creator=creator;
        this.password=password;
        this.title=title;
        this.contents=contents;
    }


}
