package com.example.scheduleproject.lv4.service;

import com.example.scheduleproject.lv4.dto.AuthorResponseDto;
import com.example.scheduleproject.lv4.dto.ScheduleRequestDto;
import com.example.scheduleproject.lv4.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv4.entity.Schedule;
import com.example.scheduleproject.lv4.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AuthorService authorService;

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

        // id 에 맞는 작성자 정보를 가져옴
        AuthorResponseDto authorById = authorService.findAuthorById(dto.getAuthorId());
        // 비밀번호가 검증
        if (!dto.getPassword().equals(authorById.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is not the same");
        }

        Schedule schedule = new Schedule(authorById.getId(), dto.getTitle(), dto.getContents());

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule(String authorId, String updatedDate, int page, int size) {
        return scheduleRepository.findAllSchedule(authorId, updatedDate, page, size);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findMemoByIdOrElseThrow(id);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, String title, String contents, String password) {

        //비번 동일한지 검증
        AuthorResponseDto authorById = authorService.findAuthorById(scheduleRepository.findMemoByIdOrElseThrow(id).getAuthorId());
        if (!authorById.getPassword().equals(password)) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password id not the same");
        }

        if (title == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and content are required values.");
        }

        int updateRow = scheduleRepository.updateSchedule(id,title,contents);
        //변화된게 없으면 0
        if (updateRow  == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No data has been modified.");
        }
        return new ScheduleResponseDto(scheduleRepository.findMemoByIdOrElseThrow(id));
    }

    @Override
    public void deleteSchedule(Long id, String password) {
        //비번 동일한지 검증
        AuthorResponseDto authorById = authorService.findAuthorById(scheduleRepository.findMemoByIdOrElseThrow(id).getAuthorId());

        if (!authorById.getPassword().equals(password)) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password id not the same");
        }
        int deletedRow = scheduleRepository.deleteSchedule(id);
        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }


    }



}
