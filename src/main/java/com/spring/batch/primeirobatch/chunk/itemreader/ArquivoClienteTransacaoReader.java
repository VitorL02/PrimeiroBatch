package com.spring.batch.primeirobatch.chunk.itemreader;

import com.spring.batch.primeirobatch.models.Cliente;
import com.spring.batch.primeirobatch.models.Transacao;
import org.springframework.batch.item.*;

public class ArquivoClienteTransacaoReader implements ItemStreamReader<Cliente> {
    private Object objAtual;
    private ItemStreamReader<Object> delegate;

    public ArquivoClienteTransacaoReader(ItemStreamReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
       delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }


    @Override
    public Cliente read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(objAtual == null)
            objAtual = delegate.read();
        Cliente cliente = (Cliente) objAtual;

        if(cliente != null){
            while (peek() instanceof Transacao)
                cliente.getTransacoes().add((Transacao) objAtual);
        }
        return cliente;

    }

    private Object peek() throws Exception {
        objAtual =delegate.read();
        return objAtual;
    }
}
