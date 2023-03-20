package br.com.db.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AtualizarEndereco(
        @NotNull(message = "id não pode ser nulo")
        Long id,
        @NotBlank(message = "CEP não pode ser vazio")
        @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 números")
        String cep,
        @NotNull(message = "Rua não pode ser nula")
        String rua
) {
}
