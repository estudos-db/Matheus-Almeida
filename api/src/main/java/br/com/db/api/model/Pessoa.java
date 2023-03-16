package br.com.db.api.model;

import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroPessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    @Column(name = "documento")
    private String cpf;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
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
