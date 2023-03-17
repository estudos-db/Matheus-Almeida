package br.com.db.api.mapping;

import br.com.db.api.dto.AtualizarPessoas;
import br.com.db.api.dto.CadastroEndereco;
import br.com.db.api.dto.CadastroPessoa;
import br.com.db.api.model.Endereco;
import br.com.db.api.model.Pessoa;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public interface PessoaMapping {

    static Pessoa converterPessoa(CadastroPessoa pessoa){
       return Pessoa.builder()
                .nome(pessoa.nome())
                .cpf(pessoa.cpf())
                .dataNascimento(pessoa.dataNascimento())
                .endereco(converterEndereco(pessoa.endereco())).build();
    }
    static Pessoa atualizarPessoa(AtualizarPessoas pessoaAtualizada, Endereco endereco, Pessoa pessoa){
        pessoa.setNome(pessoaAtualizada.nome());
        pessoa.setCpf(pessoa.getCpf());
        pessoa.setDataNascimento(pessoaAtualizada.dataNascimento());
        endereco.setRua(pessoaAtualizada.endereco().rua());
        endereco.setCep(pessoaAtualizada.endereco().cep());
        pessoa.setEndereco(endereco);
        return pessoa;
    }

    static Endereco converterEndereco(CadastroEndereco cadastroEndereco) {
        return Endereco.builder()
                .cep(cadastroEndereco.cep())
                .rua(cadastroEndereco.rua())
                .build();
    }
}
