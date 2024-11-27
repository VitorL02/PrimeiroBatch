package com.spring.batch.primeirobatch.chunk.itemwriter;

import com.spring.batch.primeirobatch.models.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

@Configuration
public class LeituraClienteArquivoFixoWriterConfig {


    @Bean
    public FlatFileItemWriter<Cliente> escritaClienteArquivoFixoWriter() {

        WritableResource writableResource = new FileSystemResource("files/arquivoClientesSaida.txt");

        return new FlatFileItemWriterBuilder<Cliente>()
                .name("escritaClienteArquivoFixoWriter")
                .resource(writableResource)
                .formatted()
                .format("%9s %9s %2s %19s")
                .names(new String[]{"nome", "sobrenome", "idade", "email"})
                .build();
    }
}
