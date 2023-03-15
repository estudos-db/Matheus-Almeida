package br.com.db.api.endereco;

import jakarta.persistence.*;
import lombok.*;

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

    public Endereco(CadastroEndereco endereco){
        this.cep = endereco.cep();
        this.rua = endereco.rua();
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
