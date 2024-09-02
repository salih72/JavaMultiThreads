package com.javaMultiThreads.contextSave.service;

import com.javaMultiThreads.contextSave.model.TextFileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class MariaDBService implements DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MariaDBService(@Qualifier("mariadbDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveData(TextFileData data) {
        String sql = "INSERT INTO text_data (content) VALUES (?)";
        jdbcTemplate.update(sql, data.getContent());
    }
}
