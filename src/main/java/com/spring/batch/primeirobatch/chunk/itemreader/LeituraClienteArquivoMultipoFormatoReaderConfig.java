package com.spring.batch.primeirobatch.chunk.itemreader;

import com.spring.batch.primeirobatch.models.Cliente;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraClienteArquivoMultipoFormatoReaderConfig {

    @Value("${file.path.multiplo.formato.resource}")
    private Resource resource;

    @Bean
    public FlatFileItemReader leituraClienteArquivoMultiploFormatoReader(LineMapper lineMapper) {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraClienteArquivoMultiploFormatoReader")
                .resource(resource)
                .lineMapper(lineMapper)
                .build();
    }
}
