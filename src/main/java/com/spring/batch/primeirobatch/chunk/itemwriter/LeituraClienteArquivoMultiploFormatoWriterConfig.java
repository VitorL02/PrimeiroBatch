package com.spring.batch.primeirobatch.chunk.itemwriter;

import com.spring.batch.primeirobatch.models.Cliente;
import com.spring.batch.primeirobatch.models.Transacao;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeituraClienteArquivoMultiploFormatoWriterConfig {
    @Bean
    public ItemWriter leituraClienteArquivoMultiploFormatoWriter() {
        return itens -> itens.forEach(System.out::println);
    }
}
