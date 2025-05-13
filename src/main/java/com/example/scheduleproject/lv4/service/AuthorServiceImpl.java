package com.example.scheduleproject.lv4.service;

import com.example.scheduleproject.lv4.dto.AuthorRequestDto;
import com.example.scheduleproject.lv4.dto.AuthorResponseDto;
import com.example.scheduleproject.lv4.entity.Author;
import com.example.scheduleproject.lv4.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorResponseDto saveId(AuthorRequestDto dto) {
        Author author = new Author(dto.getName(), dto.getEmail(), dto.getPassword());
        return authorRepository.saveId(author);
    }

    @Override
    public List<AuthorResponseDto> findAllAuthor() {
        List<AuthorResponseDto> allAuthor = authorRepository.findAllAuthor();

        // null 체크
        if (allAuthor == null) {
            throw new RuntimeException("Failed to retrieve authors: list is null");
        }

        return allAuthor;
    }

    @Override
    public AuthorResponseDto findAuthorById(Long id) {
        Author author = authorRepository.findAuthorByIdOrElseThrow(id);
        return new AuthorResponseDto(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        int delwteRow = authorRepository.deleteAuthor(id);
        if (delwteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }
}
