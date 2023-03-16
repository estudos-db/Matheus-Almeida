package br.com.db.api.dto;

import br.com.db.api.dto.AtualizarEndereco;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizarPessoas(@NotNull Long id, String nome, LocalDate dataNascimento, AtualizarEndereco endereco) {
}
