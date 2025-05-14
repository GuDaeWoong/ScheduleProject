package com.example.scheduleproject.lv4.repository;


import com.example.scheduleproject.lv4.dto.ScheduleResponseDto;
import com.example.scheduleproject.lv4.entity.Schedule;
import lombok.extern.slf4j.Slf4j;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("schedulelv3").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("authorId", schedule.getAuthorId());
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());

        LocalDateTime now = LocalDateTime.now();
        parameters.put("createdDate", now);
        parameters.put("updatedDate", now);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), schedule.getAuthorId(), schedule.getTitle(), schedule.getContents(), now, now);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule(String authorId, String updatedDate, int page, int size) {
        int offset = page * size;
        if (authorId != null && updatedDate != null) {
            return jdbcTemplate.query("select * from schedulelv3 where authorId = ? AND DATE(updatedDate) = ? order by id limit ? offset ?",
                    scheduleRowMapper(), authorId, updatedDate,size,offset);
        } else if (authorId == null && updatedDate != null) {
            return jdbcTemplate.query("select * from schedulelv3 where DATE(updatedDate) = ?  order by id limit ? offset ?",
                    scheduleRowMapper(), updatedDate,size,offset);
        }else if (authorId != null && updatedDate == null) {
            return jdbcTemplate.query("select * from schedulelv3 where authorId = ?  order by id limit ? offset ?",
                    scheduleRowMapper(), authorId,size,offset);
        } else {
            return jdbcTemplate.query("select * from schedulelv3 order by id limit ? offset ?",
                    scheduleRowMapper(),size,offset);
        }
    }

    @Override
    public Schedule findMemoByIdOrElseThrow(Long id) {
        List<Schedule> result =  jdbcTemplate.query("select * from schedulelv3 where id = ?",scheduleRowMapperV2(),id);
        return result.stream().findAny().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public int updateSchedule(Long id, String title, String contents) {
        LocalDate nowDate = LocalDate.now();
        return jdbcTemplate.update("update schedulelv3 set title = ? ,contents = ? ,updatedDate = ? where id = ?",title, contents, nowDate, id);
    }

    @Override
    public int deleteSchedule(Long id) {
        return jdbcTemplate.update("delete from schedulelv3 where id = ?", id);
    }

    private RowMapper<Schedule> scheduleRowMapperV2() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getLong("authorId"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getTimestamp("updatedDate").toLocalDateTime()

                );
            }
        };
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
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getTimestamp("updatedDate").toLocalDateTime()
                );
            }
        };
    }
}
