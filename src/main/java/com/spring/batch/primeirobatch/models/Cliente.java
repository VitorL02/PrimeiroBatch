package com.spring.batch.primeirobatch.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    @NotNull
    @Size(min = 1,max = 100)
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Nome deve ser alfabetico")
    private String nome;
    @NotNull
    private String sobrenome;

    @NotNull
    @Range(min = 18,max = 130)
    private Integer idade;

    @NotNull
    @Size(min = 1,max = 50)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email invalido")
    private String email;

    private List<Transacao> transacoes = new ArrayList<>();

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", email='" + email + '\'' +
                '}';
    }
}
