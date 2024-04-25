package com.example.greeting.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import com.example.greeting.entities.Greeting;
import com.example.greeting.exception.GreetingNotFoundException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Slf4j
@Repository
@Qualifier("GreetingDbStorage")
public class GreetingDbStorage implements GreetingStorage {
    private final JdbcTemplate jdbcTemplate;
    private static final String SQL_INSERT_GREETING = "INSERT INTO table_greeting (content, data) VALUES( ?, ?)";
    private static final String SQL_GET_GREETING = "SELECT * FROM table_greeting WHERE greeting_id = ?";
    private static final String SQL_GET_ALL_GREETING = "SELECT * FROM table_greeting";

    @Autowired
    public GreetingDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveGreeting(Greeting greeting) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_GREETING, new String[]{"greeting_id"});
                    stmt.setString(1, greeting.getContent());
                    stmt.setDate(2, Date.valueOf(greeting.getData()));
                    return stmt;
                }, keyHolder);
        greeting.setGreetingId(keyHolder.getKey().longValue());
//        log.info("GreetingDbStorage: Добавлен Greeting = {}", greeting);
        getGreetingById(greeting.getGreetingId());
    }

    @Override
    public Greeting getGreetingById(Long id) {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(SQL_GET_GREETING, id);
        if (rowSet.next()) {
            Greeting greeting = new Greeting();
            greeting.setGreetingId(rowSet.getLong("greeting_id"));
            greeting.setContent(rowSet.getString("content"));
            greeting.setData(rowSet.getDate("data").toLocalDate());
//            log.info("GreetingDbStorage: GET: Получен Greeting = {}", greeting);
            return greeting;
        } else {
            throw new GreetingNotFoundException("GreetingDbStorage: Отсутствует Greeting с идентификатором: " + id);
        }
    }

    @Override
    public Collection<Greeting> getAllGreetings() {
//        log.info("GreetingDbStorage: Получены все Greetings");
        return jdbcTemplate.query(SQL_GET_ALL_GREETING, this::mapRowToGreeting);
    }

    private Greeting mapRowToGreeting(ResultSet resultSet, int rowNum) {
        try {
//            return Greeting.builder()
//                    .greetingId(resultSet.getLong("greeting_id"))
//                    .content(resultSet.getString("content"))
//                    .data(LocalDate.parse(resultSet.getString("data")))
//                    .build();
            Greeting greeting = new Greeting();
            greeting.setGreetingId(resultSet.getLong("greeting_id"));
            greeting.setContent(resultSet.getString("content"));
            greeting.setData(resultSet.getDate("data").toLocalDate());
            return greeting;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}