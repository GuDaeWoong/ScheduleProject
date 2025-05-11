package com.example.scheduleproject.lv2.service;

import com.example.scheduleproject.lv2.dto.ScheduleRequestDto;
import com.example.scheduleproject.lv2.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv2.entity.Schedule;
import com.example.scheduleproject.lv2.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getCreator(), dto.getPassword(), dto.getTitle(),dto.getContents());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        List<ScheduleResponseDto> allSchedule = scheduleRepository.findAllSchedule();

        // 내림차순으로 정렬
        return allSchedule.stream()
                .sorted(Comparator.comparing(ScheduleResponseDto::getUpdatedDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }


    @Override
    public ScheduleResponseDto updateSchedule(Long id,  String title, String contents) {

        if (title == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The title and content are required values.");
        }
        //수정
        int updatedRow = scheduleRepository.updateSchedule(id,title,contents);

        // 수정된 row가 0개라면
        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data has been modified.");
        }

        return new ScheduleResponseDto(scheduleRepository.findScheduleById(id).get());
    }

    @Override
    public void deleteSchedule(Long id) {
        // 일정 삭제
        int deletedRow = scheduleRepository.deleteSchedule(id);
        // 삭제된 row가 0개 라면
        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }
}
