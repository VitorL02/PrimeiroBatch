package com.spring.batch.primeirobatch.chunk.itemwriter;

import com.spring.batch.primeirobatch.models.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraClienteArquivoLimitadoWriterConfig {
    @Bean
    public ItemWriter<Cliente> leituraClienteArquivoLimitadoWriter() {
        return itens -> itens.forEach(System.out::println);
    }
}
