package com.spring.batch.primeirobatch.chunk.itemreader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class LeituraMultiposArquivosClienteTransacaoReaderConfig {

    @Value("${file.path.leitura.multipla.1.resource}")
    private Resource resource;

    @Value("${file.path.leitura.multipla.2.resource}")
    private Resource resource2;

    @Value("${file.path.leitura.multipla.3.resource}")
    private Resource resource3;

    @Bean
    public MultiResourceItemReader leituraMultiplosArquivosClienteTransacaoReader(@Qualifier("leituraClienteArquivoMultiploFormatoReader") FlatFileItemReader leituraArquivoMultiplosFormatosReader) {
       Resource [] resources = new Resource[]{
               resource,   resource2  ,resource3
       };
        return new MultiResourceItemReaderBuilder<>()
                .name("leituraMultiplosArquivosClienteTransacaoReader")
                .resources(resources)
                .delegate(new ArquivoClienteTransacaoReader(leituraArquivoMultiplosFormatosReader))
                .build();
    }
}
