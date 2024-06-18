package com.spring.batch.primeirobatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class BatchConfig {

    @Bean
    public Job imprimeOlaJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("primeiroJob", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager, Tasklet imprimeStringTasklet) {
        return new StepBuilder("step", jobRepository)
                .tasklet(imprimeStringTasklet, transactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet imprimeStringTasklet(@Value("#{jobParameters['nome']}") String name) {
        return new ImprimeStringTasklet(name);
    }

    public static class ImprimeStringTasklet implements Tasklet {

        private final String name;

        public ImprimeStringTasklet(String name) {
            this.name = name;
        }

        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
            System.out.println("Ola " + name);
            return RepeatStatus.FINISHED;
        }
    }
}