package br.com.db.api.pessoa;

import br.com.db.api.endereco.AtualizarEndereco;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizarPessoas(@NotNull Long id, String nome, LocalDate dataNascimento, AtualizarEndereco endereco) {
}
