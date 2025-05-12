package com.example.scheduleproject.lv3.service;

import com.example.scheduleproject.lv3.dto.AuthorResponseDto;
import com.example.scheduleproject.lv3.dto.ScheduleRequestDto;
import com.example.scheduleproject.lv3.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv3.entity.Schedule;
import com.example.scheduleproject.lv3.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AuthorService authorService;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, AuthorService authorService) {
        this.scheduleRepository = scheduleRepository;
        this.authorService = authorService;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

        // id 에 맞는 작성자 정보를 가져옴
        AuthorResponseDto authorById = authorService.findAuthorById(dto.getAuthorId());

        // 비밀번호가 검증
        if (!dto.getPassword().equals(authorById.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password is not the same");
        }

        Schedule schedule = new Schedule(authorById.getId(),dto.getTitle(),dto.getContents());

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllByAuthorSchedule(Long authorId) {
        List<ScheduleResponseDto> allByAuthorSchedule = scheduleRepository.findAllByAuthorSchedule(authorId);
        return allByAuthorSchedule;
    }
}
