package com.spring.batch.primeirobatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ImprimeParImparStepConfig {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step imprimeParImparStep(ItemReader<Integer> contaAteDezReader,
                                    ItemProcessor<Integer, String> processaContador, ItemWriter<String> imprimeWriter){
        return new StepBuilder("imprimeParImpar",jobRepository)
                .<Integer,String>chunk(1,transactionManager)
                .reader(contaAteDezReader)
                .processor(processaContador)
                .writer(imprimeWriter)
                .build();
    }







}
