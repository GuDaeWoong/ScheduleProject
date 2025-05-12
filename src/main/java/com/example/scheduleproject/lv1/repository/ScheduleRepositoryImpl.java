package com.example.scheduleproject.lv1.repository;

import com.example.scheduleproject.lv1.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv1.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("creator", schedule.getCreator());
        parameters.put("password", schedule.getPassword());
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());
        LocalDateTime now = LocalDateTime.now();
        //포멧 설정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        parameters.put("updatedDate", formattedDate);


        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        // ResponseDto 형태로 포장
        return new ScheduleResponseDto( key.longValue(),schedule.getCreator(),schedule.getPassword(),schedule.getTitle(),schedule.getContents(),formattedDate);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return jdbcTemplate.query("select * from schedule",scheduleRowMapper());
    }

    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        List<Schedule> result =  jdbcTemplate.query("select * from schedule where id = ? ",scheduleRowMapperV2());
        return result.stream().findAny();//Optional 형태로 변환
    }

    @Override
    public Schedule findScheduleByIdOrElseThrow(String creator) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where creator = ?", scheduleRowMapperV2(), creator);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + creator));
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("creator"),
                        rs.getString("password"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("updatedDate")
                );
            }
        };

    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("creator"),
                        rs.getString("password"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("updatedDate")
                );
            }
        };
    }
}
