package br.com.db.api.model;

import br.com.db.api.dto.AtualizarEndereco;
import br.com.db.api.dto.CadastroEndereco;
import br.com.db.api.dto.CadastroPessoa;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String rua;

    public Endereco(CadastroEndereco cadastroEndereco) {
        this.cep = cadastroEndereco.cep();
        this.rua = cadastroEndereco.rua();
    }


    public void atualizarEndereco(AtualizarEndereco endereco) {
        if (endereco.cep() != null){
            this.cep = endereco.cep();
        }
        if (endereco.rua() != null){
            this.rua = endereco.rua();
        }
    }
}
