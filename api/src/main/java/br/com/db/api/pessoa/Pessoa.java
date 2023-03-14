package br.com.db.api.pessoa;

import br.com.db.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    @Embedded
    private Endereco endereco;

    public Pessoa(CadastroPessoa pessoa) {
        this.nome = pessoa.nome();
        this.dataNascimento = pessoa.dataNascimento();
        this.cpf = pessoa.cpf();
        this.endereco = new Endereco(pessoa.endereco());
    }

    public void atualizar(AtualizarPessoas pessoas) {
        if (pessoas.nome() != null) {
            this.nome = pessoas.nome();
        }
        if (pessoas.dataNascimento() != null) {
            this.dataNascimento = pessoas.dataNascimento();
        }
        if (pessoas.endereco() != null) {
            this.endereco.atualizarEndereco(pessoas.endereco());
        }
    }
}
