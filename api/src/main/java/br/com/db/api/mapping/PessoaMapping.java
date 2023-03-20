package br.com.db.api.mapping;

import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroPessoa;
import br.com.db.api.model.Endereco;
import br.com.db.api.model.Pessoa;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.db.api.mapping.EnderecoMapping.converterEndereco;


@Component
public interface PessoaMapping {

    static Pessoa converter(CadastroPessoa pessoa){
       return Pessoa.builder()
                .nome(pessoa.nome())
                .cpf(pessoa.cpf())
                .dataNascimento(pessoa.dataNascimento())
                .enderecos(converterEndereco(pessoa.enderecos())).build();
    }
    static Pessoa converter(AtualizarPessoas pessoaAtualizada, Endereco endereco, Pessoa pessoa){
        pessoa.setNome(pessoaAtualizada.nome());
        pessoa.setCpf(pessoa.getCpf());
        pessoa.setDataNascimento(pessoaAtualizada.dataNascimento());
        endereco.setRua(pessoaAtualizada.endereco().rua());
        endereco.setCep(pessoaAtualizada.endereco().cep());
        pessoa.setEnderecos(List.of(endereco));
        return pessoa;
    }
}
