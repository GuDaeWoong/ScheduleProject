package com.example.scheduleproject.lv3.controller;

import com.example.scheduleproject.lv3.dto.AuthorRequestDto;
import com.example.scheduleproject.lv3.dto.AuthorResponseDto;
import com.example.scheduleproject.lv3.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //회원가입
    @PostMapping
    private ResponseEntity<AuthorResponseDto> createId(@RequestBody AuthorRequestDto dto) {
        return new ResponseEntity<>(authorService.saveId(dto), HttpStatus.CREATED);
    }

    // 회원 리스트 조회
    @GetMapping
    public List<AuthorResponseDto> findAllAuthor() {
        return authorService.findAllAuthor();
    }

    // 회원 조회
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> findAuthorById(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.findAuthorById(id),HttpStatus.OK);
    }

    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(
            @PathVariable Long id,
            @RequestParam String password) {
    AuthorResponseDto searchId = authorService.findAuthorById(id);
        if (!searchId.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password is not the same");
        }
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
