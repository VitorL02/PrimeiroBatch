package com.spring.batch.primeirobatch.chunk.itemreader;

import com.spring.batch.primeirobatch.models.Cliente;
import com.spring.batch.primeirobatch.models.Transacao;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClienteTransacaoLineMapper {

    @Bean
    public PatternMatchingCompositeLineMapper lineMapper(){
        PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
        lineMapper.setTokenizers(tokenizers());
        lineMapper.setFieldSetMappers(fieldSetMappers());
        return lineMapper;
    }

    private Map<String, FieldSetMapper> fieldSetMappers() {
        Map<String,FieldSetMapper>fieldSetMapperMap = new HashMap<>();
        fieldSetMapperMap.put("0*",fieldSetMapperMap(Cliente.class));
        fieldSetMapperMap.put("1*",fieldSetMapperMap(Transacao.class));
        return fieldSetMapperMap;
    }

    private FieldSetMapper fieldSetMapperMap(Class classe) {
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(classe);
        return fieldSetMapper;
    }

    private Map<String, LineTokenizer> tokenizers() {
        Map<String,LineTokenizer> tokenizer = new HashMap<>();
        tokenizer.put("0*",clienteTokenizer());
        tokenizer.put("1*",transacaoLineTokenizers());
        return tokenizer;
    }

    private LineTokenizer transacaoLineTokenizers() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id","descricao","valor");
        lineTokenizer.setIncludedFields(1,2,3);
        return lineTokenizer;
    }

    private LineTokenizer clienteTokenizer() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("nome","sobrenome","idade","email");
        lineTokenizer.setIncludedFields(1,2,3,4);
        return lineTokenizer;
    }


}
