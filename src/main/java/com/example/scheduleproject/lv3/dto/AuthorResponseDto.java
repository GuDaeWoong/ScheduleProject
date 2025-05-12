package com.example.scheduleproject.lv3.dto;

import com.example.scheduleproject.lv3.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorResponseDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private String createdDate;
    private String updatedDate;

    public AuthorResponseDto(Author author) {
        this.id = author.getId();
        this.name =author.getName();
        this.email = author.getEmail();
        this.password = author.getPassword();
        this.createdDate = author.getCreatedDate();
        this.updatedDate = author.getUpdatedDate();

    }


}
