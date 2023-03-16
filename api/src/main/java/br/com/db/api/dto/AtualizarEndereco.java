package br.com.db.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AtualizarEndereco(
        @Pattern(regexp = "\\d{8}")
        String cep,
        String rua
) {
}
