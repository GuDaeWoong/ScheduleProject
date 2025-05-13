package com.example.scheduleproject.lv4.dto;

import com.example.scheduleproject.lv4.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AuthorResponseDto {
    private long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDate updatedDate;

    public AuthorResponseDto(Author author) {
        this.id = author.getId();
        this.name =author.getName();
        this.email = author.getEmail();
        this.password = author.getPassword();
        this.createdDate = author.getCreatedDate();
        this.updatedDate = author.getUpdatedDate();

    }

}
