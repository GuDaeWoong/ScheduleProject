package com.example.scheduleproject.lv3.service;

import com.example.scheduleproject.lv3.dto.AuthorRequestDto;
import com.example.scheduleproject.lv3.dto.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto saveId(AuthorRequestDto dto);

    List<AuthorResponseDto> findAllAuthor();

    AuthorResponseDto findAuthorById(Long id);

    void deleteAuthor(Long id);
}
