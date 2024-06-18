package com.spring.batch.primeirobatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
public class ImprimeOlaMundoStepConfig {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step imprimeOlaMundoStep(Tasklet imprimeTasklet) {
        return new StepBuilder("imprimeOlaMundoStep", jobRepository)
                .tasklet(imprimeTasklet, transactionManager)
                .build();
    }
}