package com.example.scheduleproject.lv3.repository;

import com.example.scheduleproject.lv3.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv3.entity.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("schedulelv3").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("authorId", schedule.getAuthorId());
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(formatter);
        parameters.put("createdDate", formattedDate);
        parameters.put("updatedDate", formattedDate);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(),schedule.getAuthorId(),schedule.getTitle(),schedule.getContents(),formattedDate,formattedDate);
    }

    @Override
    public List<ScheduleResponseDto> findAllByAuthorSchedule(Long authorId) {
        return jdbcTemplate.query("select * from schedulelv3 where authorId = ? ",scheduleRowMapper(),authorId);
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getLong("authorId"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("createdDate"),
                        rs.getString("updatedDate")
                );
            }
        };
    }
}
