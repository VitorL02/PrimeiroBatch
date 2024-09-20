package com.spring.batch.primeirobatch.step;

import com.spring.batch.primeirobatch.models.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class LeituraArquivoLimitadoStepConfig {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step arquivoLimitadoStep(ItemReader<Cliente> leituraClienteArquivoLimitadoReader, ItemWriter<Cliente> leituraClienteArquivoLimitadoWriter){
        return new StepBuilder("arquivoLimitadoStep",jobRepository)
                .<Cliente,Cliente>chunk(1,transactionManager)
                .reader(leituraClienteArquivoLimitadoReader)
                .writer(leituraClienteArquivoLimitadoWriter)
                .build();
    }







}