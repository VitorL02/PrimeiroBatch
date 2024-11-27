package com.spring.batch.primeirobatch.chunk.itemwriter;

import com.spring.batch.primeirobatch.models.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;

@Configuration
public class LeituraClienteArquivoLimitadoWriterConfig {
    @Bean

    public FlatFileItemWriter<Cliente> leituraClienteArquivoLimitadoWriter() {
        WritableResource writableResource = new FileSystemResource("files/arquivoClienteDelimitado.txt");

        return new FlatFileItemWriterBuilder<Cliente>()
                .name("leituraClienteArquivoLimitadoWriter")
                .resource(writableResource)
                .delimited()
                .names("nome","sobrenome","idade","email")
                .build();
    }
}
