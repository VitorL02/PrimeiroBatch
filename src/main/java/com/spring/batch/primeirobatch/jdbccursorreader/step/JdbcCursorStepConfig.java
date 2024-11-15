package com.spring.batch.primeirobatch.jdbccursorreader.step;

import com.spring.batch.primeirobatch.models.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
public class JdbcCursorStepConfig {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step jdbcCursorStep (JdbcCursorItemReader<Cliente> jdbcCursorItemReader, ItemWriter<Cliente> jdbcCursorWriter ){
        return new StepBuilder("jdbcCursorStep",jobRepository)
                .<Cliente,Cliente>chunk(1,transactionManager)
                .reader(jdbcCursorItemReader)
                .writer(jdbcCursorWriter)
                .build();
    }
}
