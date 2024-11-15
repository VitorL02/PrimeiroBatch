package com.spring.batch.primeirobatch.jdbccursorreader.config;

import com.spring.batch.primeirobatch.models.Cliente;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;


@Configuration
public class JdbcCursorReaderConfig {
    @Bean
    public JdbcCursorItemReader<Cliente>jdbcCursorItemReader(@Qualifier("appDataSource") DataSource dataSource){
        return new JdbcCursorItemReaderBuilder<Cliente>()
                .name("jdbcCursorItemReader")
                .dataSource(dataSource)
                .sql("SELECT * FROM cliente")
                .rowMapper(new BeanPropertyRowMapper<Cliente>(Cliente.class))
                .build();
    }
}
