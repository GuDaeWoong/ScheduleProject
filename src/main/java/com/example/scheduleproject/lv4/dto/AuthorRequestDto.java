package com.example.scheduleproject.lv4.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AuthorRequestDto {

    private String name;

    @Email(message = "이메일 형식인지 검증해주세요.")
    private String email;

    @NotNull(message = "값이 비어있습니다 값을 넣어주세요")
    @NotBlank(message = "공백이 있습니다. 다시 입력해 주세요")
    private String password;
}
