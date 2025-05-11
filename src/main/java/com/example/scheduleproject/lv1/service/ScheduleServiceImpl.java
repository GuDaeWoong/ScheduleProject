package com.example.scheduleproject.lv1.service;

import com.example.scheduleproject.lv1.dto.ScheduleRequestDto;
import com.example.scheduleproject.lv1.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv1.entity.Schedule;
import com.example.scheduleproject.lv1.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService{

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


}
