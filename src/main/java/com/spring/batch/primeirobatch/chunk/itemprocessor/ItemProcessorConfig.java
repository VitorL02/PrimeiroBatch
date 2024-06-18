package com.spring.batch.primeirobatch.chunk.itemprocessor;

import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemProcessorConfig {
    @Bean
    public FunctionItemProcessor<Integer, String> processaContador() {
        return new FunctionItemProcessor<Integer,String>(item -> item % 2 == 0 ? String.format("Item %s é Par",item) : String.format("Item %s é Impar",item));
    }
}
