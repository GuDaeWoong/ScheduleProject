package com.example.scheduleproject.lv3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Author {

    private long id;
    private String name;
    private String email;
    private String password;
    private String createdDate;
    private String updatedDate;


    public Author(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }
}
