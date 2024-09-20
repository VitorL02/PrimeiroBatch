package com.spring.batch.primeirobatch.chunk.itemreader;

import com.spring.batch.primeirobatch.models.Cliente;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraClienteArquivoLimitadoReaderConfig {

    @Value("${file.path.limitado.resource}")
    private Resource resource;

    @Bean
    public FlatFileItemReader<Cliente> leituraClienteArquivoLimitadoReader() {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraClienteArquivoLimitadoReader")
                .resource(resource)
                .delimited()
                .names(new String[]{"nome","sobrenome","idade","email"})
                .targetType(Cliente.class)
                .build();
    }
}
