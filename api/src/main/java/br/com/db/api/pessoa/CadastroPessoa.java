package br.com.db.api.pessoa;

import br.com.db.api.endereco.CadastroEndereco;
import br.com.db.api.endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CadastroPessoa(
        @NotBlank
        String nome,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,fallbackPatterns = {"yyyy-MM-dd", "dd/MM/yyyy"})
        LocalDate dataNascimento,
        @CPF
        @NotBlank
        String cpf,
        @Valid
        CadastroEndereco endereco

) {
}
