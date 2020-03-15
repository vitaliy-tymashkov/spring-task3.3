package com.epam.learn.service;

import java.util.List;
import com.epam.learn.model.City;
import com.epam.learn.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<City> findAll() {

        String sql = "SELECT * FROM cities";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(City.class));
    }
}

