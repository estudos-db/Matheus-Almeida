package br.com.db.api.dto;

import br.com.db.api.dto.CadastroEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public record CadastroPessoa(
        @NotBlank(message = "Nome não pode ser vazio")
        String nome,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,fallbackPatterns = {"yyyy-MM-dd", "dd/MM/yyyy"})
        LocalDate dataNascimento,
        @CPF(message = "Cpf invalido")
        @NotBlank
        String cpf,
        @Valid
        List<CadastroEndereco> enderecos

) {
}
