package com.example.scheduleproject.lv3.controller;


import com.example.scheduleproject.lv3.dto.ScheduleRequestDto;
import com.example.scheduleproject.lv3.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv3.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/scheduleLv3")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 글 작성하기
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleRequestDto dto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);
    }

    // 작성자의 고유 식별자를 받아 일정이 검색될 수 있도록 수정
    @GetMapping("/{authorId}")
    public List<ScheduleResponseDto> findAllByAuthorSchedule(@PathVariable Long authorId ) {
        return scheduleService.findAllByAuthorSchedule(authorId);
    }

}
