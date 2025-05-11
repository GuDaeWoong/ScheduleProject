package com.example.scheduleproject.lv2.controller;


import com.example.scheduleproject.lv2.dto.ScheduleRequestDto;
import com.example.scheduleproject.lv2.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv2.entity.Schedule;
import com.example.scheduleproject.lv2.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/scheduleLv2")
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
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    // 비밀번호 동일시 선택 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto,
            @RequestParam String password
    ) {
        ScheduleResponseDto searchId = scheduleService.findScheduleById(id);

        if (searchId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }
        if (!searchId.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is not the same");
        }
        ScheduleResponseDto updatedSchedule = scheduleService.updateSchedule(id, dto.getTitle(),dto.getContents());
    return new ResponseEntity<>( updatedSchedule ,HttpStatus.OK);
    }

    // 비밀번호 동일시 선택 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestParam String password) {

        ScheduleResponseDto searchId = scheduleService.findScheduleById(id);
        if (!searchId.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is not the same");
        }

        scheduleService.deleteSchedule(id);

        return  new ResponseEntity<>(HttpStatus.OK);
    }


}
