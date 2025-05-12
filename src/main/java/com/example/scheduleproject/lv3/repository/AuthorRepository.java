package com.example.scheduleproject.lv3.repository;

import com.example.scheduleproject.lv3.dto.AuthorResponseDto;
import com.example.scheduleproject.lv3.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    AuthorResponseDto saveId(Author author);

    List<AuthorResponseDto> findAllAuthor();

    Optional<Author> findAuthorById(Long id);

    Author findAuthorByIdOrElseThrow(Long id);

    int deleteAuthor(Long id);
}
