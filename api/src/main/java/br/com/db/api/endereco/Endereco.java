package br.com.db.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
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
