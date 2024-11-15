package com.spring.batch.primeirobatch.processor.validadores.step;

import com.spring.batch.primeirobatch.models.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class ProcessorValidadoresStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step processorValidadoresStep (FlatFileItemReader<Cliente> processadorValidacaoReader, ItemWriter<Cliente> processadorValidacaoWriter, ItemProcessor<Cliente,Cliente> processaValidacaoProcessor){
        return new StepBuilder("processorValidadoresStep",jobRepository)
                .<Cliente,Cliente>chunk(1,transactionManager)
                .reader(processadorValidacaoReader)
                .processor(processaValidacaoProcessor)
                .writer(processadorValidacaoWriter)
                .build();
    }
}
