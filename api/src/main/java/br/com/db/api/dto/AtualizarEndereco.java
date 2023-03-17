package br.com.db.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AtualizarEndereco(
        @NotNull
        Long id,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotNull
        String rua
) {
}
