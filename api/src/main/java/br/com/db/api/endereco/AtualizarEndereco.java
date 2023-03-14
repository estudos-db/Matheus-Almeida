package br.com.db.api.endereco;

import jakarta.validation.constraints.Pattern;

public record AtualizarEndereco(
        @Pattern(regexp = "\\d{8}")
        String cep,
        String rua
) {
}
