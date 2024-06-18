package com.spring.batch.primeirobatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


@Configuration
public class BatchConfig {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager transactionManager ;

    @Bean
    public Job imprimeOlaJob(Step step) {
        return new JobBuilder("primeiroJob", jobRepository)
                .start(step)
                .next(imprimeParImparStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step(Tasklet imprimeStringTasklet) {
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
    @Bean
    public Step imprimeParImparStep(){
        return new StepBuilder("imprimeParImpar",jobRepository)
                .<Integer,String>chunk(1,transactionManager)
                .reader(contaAteDezReader())
                .processor(processaContador())
                .writer(imprimeWriter())
                .build();
    }

    private ItemWriter<String> imprimeWriter() {
        return itens -> itens.forEach(System.out::println);
    }

    private FunctionItemProcessor<Integer, String> processaContador() {
        return new FunctionItemProcessor<Integer,String>(item -> item % 2 == 0 ? String.format("Item %s é Par",item) : String.format("Item %s é Impar",item));
    }

    private IteratorItemReader<Integer> contaAteDezReader() {
        List<Integer> numerosUmAteDez = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        return new IteratorItemReader<Integer>(numerosUmAteDez.iterator());
    }
}