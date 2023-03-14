package br.com.db.api.pessoa;

import java.time.LocalDate;

public record ListagemPessoas(String nome, LocalDate dataNascimento, String cpf) {
    public ListagemPessoas(Pessoa pessoa){
        this(pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getCpf());
    }
}
