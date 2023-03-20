package br.com.db.api.mapping;


import br.com.db.api.dto.CadastroEndereco;
import br.com.db.api.model.Endereco;

import java.util.List;
import java.util.stream.Collectors;

public interface EnderecoMapping {
    static List<Endereco> converterEndereco(List<CadastroEndereco> cadastroEnderecoList) {
        return cadastroEnderecoList.stream().map(EnderecoMapping::converterEndereco).collect(Collectors.toList());
    }
    static Endereco converterEndereco(CadastroEndereco cadastroEndereco) {
        return Endereco.builder()
                .cep(cadastroEndereco.cep())
                .rua(cadastroEndereco.rua())
                .build();
    }

}
