package com.spring.batch.primeirobatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class BatchConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;



//    @Bean
//    public Job processorValidadores(@Qualifier("processorValidadoresStep") Step processorValidadoresStep) {
//        return new JobBuilder("processorValidadoresStep", jobRepository)
//                .start(processorValidadoresStep)
//                .incrementer(new RunIdIncrementer())
//                .build();
//    }

//    @Bean
//    public Job leituraBancoCursor(Step jdbcCursorStep) {
//        return new JobBuilder("leituraBancoCursor", jobRepository)
//                .start(jdbcCursorStep)
//                .incrementer(new RunIdIncrementer())
//                .build();
//    }


    @Bean
    public Job imprimeOlaJob(Step imprimeOlaMundoStep,Step imprimeParImparStep,Step arquivoFixoStep,Step arquivoLimitadoStep,Step arquivoMultiploFormatoStep,Step multiplosArquivosStep) {
        return new JobBuilder("imprimeOlaJob", jobRepository)
                .start(imprimeOlaMundoStep)
//              .next(imprimeParImparStep)
//                .next(arquivoFixoStep)
                  .next(arquivoLimitadoStep)
//              .next(arquivoMultiploFormatoStep)
//               .next(multiplosArquivosStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }



}