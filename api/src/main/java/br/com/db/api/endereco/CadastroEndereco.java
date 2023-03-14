package br.com.db.api.endereco;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastroEndereco(
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotNull
        String rua) {
}
