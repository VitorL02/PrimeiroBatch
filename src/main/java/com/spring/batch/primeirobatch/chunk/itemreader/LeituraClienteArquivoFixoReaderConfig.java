package com.spring.batch.primeirobatch.chunk.itemreader;

import com.spring.batch.primeirobatch.models.Cliente;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LeituraClienteArquivoFixoReaderConfig {

    @Value("${file.path.resource}")
    private Resource resource;

    @Bean
    public FlatFileItemReader<Cliente> leituraClienteArquivoFixoReader() {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraClienteArquivoFixoReader")
                .resource(resource)
                .fixedLength()
                .columns(new Range[]{new Range(1,10),new Range(11,20),new Range(21,23),new Range(24,43)})
                .names(new String[]{"nome","sobrenome","idade","email"})
                .targetType(Cliente.class)
                .build();
    }
}
