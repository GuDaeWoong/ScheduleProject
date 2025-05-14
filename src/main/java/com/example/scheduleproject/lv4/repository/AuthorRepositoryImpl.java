package com.example.scheduleproject.lv4.repository;

import com.example.scheduleproject.lv4.dto.AuthorResponseDto;
import com.example.scheduleproject.lv4.entity.Author;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuthorRepositoryImpl(JdbcTemplate dataSource) {
        this.jdbcTemplate = dataSource;
    }

    @Override
    public AuthorResponseDto saveId(Author author) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("author")
                .usingGeneratedKeyColumns("id");


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", author.getName());
        parameters.put("email", author.getEmail());
        parameters.put("password", author.getPassword());
        LocalDateTime now = LocalDateTime.now();
        parameters.put("createdDate", now);
        parameters.put("updatedDate", now);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new AuthorResponseDto(key.longValue(), author.getName(), author.getEmail(), author.getPassword(), now, now
        );
    }

    @Override
    public List<AuthorResponseDto> findAllAuthor() {
        return jdbcTemplate.query("select * from author", authorRowMapper());
    }

    @Override
    public Author findAuthorByIdOrElseThrow(Long id) {
        List<Author> result = jdbcTemplate.query("select * from author where id = ?", authorRowMapperV2(), id);
        //
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    @Override
    public Optional<Author> findAuthorById(Long id) {
        List<Author> result = jdbcTemplate.query("select * from author where id = ? ", authorRowMapperV2(), id);
        return result.stream().findAny();//Optional 형태로 변환
    }

    @Override
    public int deleteAuthor(Long id) {
        // 1. 해당 authorId를 가진 schedulelv3 데이터 먼저 삭제
        jdbcTemplate.update("delete from schedulelv3 where authorId=?", id);

        return jdbcTemplate.update("delete from author where id=?", id);
    }

    private RowMapper<Author> authorRowMapperV2() {
        return new RowMapper<Author>() {
            @Override
            public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Author(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getTimestamp("updatedDate").toLocalDateTime()

                );
            }
        };
    }

    private RowMapper<AuthorResponseDto> authorRowMapper() {
        return new RowMapper<AuthorResponseDto>() {
            @Override
            public AuthorResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new AuthorResponseDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getTimestamp("updatedDate").toLocalDateTime()
                );
            }
        };
    }
}
