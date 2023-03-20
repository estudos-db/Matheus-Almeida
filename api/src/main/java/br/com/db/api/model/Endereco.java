package br.com.db.api.model;

import br.com.db.api.dto.AtualizarEndereco;
import br.com.db.api.dto.CadastroEndereco;
import br.com.db.api.dto.CadastroPessoa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "CEP não pode ser vazio")
    private String cep;
    @NotNull(message = "Rua não pode ser nula")
    private String rua;
}
