package com.spring.batch.primeirobatch.processor.validadores.reader;

import com.spring.batch.primeirobatch.models.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ProcessadorValidacaoReaderConfig {


    @Value("${file.path.leitura.clientesValidosInvalidos.resource}")
    private Resource resource;

    @Bean
    public FlatFileItemReader<Cliente> processadorValidacaoReader () {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("processadorValidacaoReader")
                .resource(resource)
                .delimited()
                .names("nome", "idade", "email")
                .targetType(Cliente.class)
                .build();
    }
}
