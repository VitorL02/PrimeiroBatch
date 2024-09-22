package com.spring.batch.primeirobatch.step;

import com.spring.batch.primeirobatch.chunk.itemreader.ArquivoClienteTransacaoReader;
import com.spring.batch.primeirobatch.models.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class LeituraArquivoMultiploFormatoStepConfig {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step arquivoMultiploFormatoStep(FlatFileItemReader leituraClienteArquivoMultiploFormatoReader, ItemWriter leituraClienteArquivoMultiploFormatoWriter){
        return new StepBuilder("arquivoMultiploFormatoStep",jobRepository)
                .<Cliente,Cliente>chunk(1,transactionManager)
                .reader(new ArquivoClienteTransacaoReader(leituraClienteArquivoMultiploFormatoReader))
                .writer(leituraClienteArquivoMultiploFormatoWriter)
                .build();

    }







}
