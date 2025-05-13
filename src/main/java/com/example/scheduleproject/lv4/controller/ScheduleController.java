package com.example.scheduleproject.lv4.controller;


import com.example.scheduleproject.lv4.dto.ScheduleRequestDto;
import com.example.scheduleproject.lv4.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv4.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    // 전체 일정 조회 API
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule(
            @RequestParam(name = "authorId", required = false) String authorId,
            @RequestParam(name = "updatedDate", required = false) String updatedDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size

    ) {
        return scheduleService.findAllSchedule(authorId, updatedDate, page, size);
    }
//    // 전체 일정 조회 API
//    @GetMapping
//    public List<ScheduleResponseDto> findAllSchedule(
//            @RequestParam(name = "authorId", required = false) String authorId,
//            @RequestParam(name = "updatedDate", required = false) String updatedDate
//    ) {
//        return scheduleService.findAllSchedule(authorId, updatedDate);
//    }

    //일정 id 기준 조회
    @GetMapping("/{id}")
    public  ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id),HttpStatus.OK);
    }

    // 일정 업데이트
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto dto,
            @RequestParam String password) {

        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto.getTitle(),dto.getContents(),password), HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id,
                                               @RequestParam String password
    ) {
        scheduleService.deleteSchedule(id,password);
        return new ResponseEntity<>(HttpStatus.OK);
    }








}
