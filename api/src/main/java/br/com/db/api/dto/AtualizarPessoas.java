package br.com.db.api.dto;

import br.com.db.api.dto.AtualizarEndereco;
import br.com.db.api.model.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record AtualizarPessoas(
        @NotNull Long id,
        @NotBlank String nome,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,fallbackPatterns = {"yyyy-MM-dd", "dd/MM/yyyy"})
        LocalDate dataNascimento,
        @Valid
        AtualizarEndereco endereco) {
}
