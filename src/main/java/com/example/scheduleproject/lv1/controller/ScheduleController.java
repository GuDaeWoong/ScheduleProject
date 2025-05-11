package com.example.scheduleproject.lv1.controller;


import com.example.scheduleproject.lv1.dto.ScheduleRequestDto;
import com.example.scheduleproject.lv1.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv1.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/scheduleLv1")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 등록
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleRequestDto dto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);
    }

    // 일정 모두 조회
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule() {
        return scheduleService.findAllSchedule();
    }

    // 선택 일정 조회
    // 식별자(ID)를 사용하여 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id),HttpStatus.OK);
    }
//    // 조회 모두 조회
//    @GetMapping("/{eventId}")
//    public findScheduleById()
//
//
//    // 책 목록 조회
//    @GetMapping()
//
//    //책 수정
//    @PutMapping("/{eventId}")
//
//    //책 삭제
//    @DeleteMapping("/{eventId}")


}
